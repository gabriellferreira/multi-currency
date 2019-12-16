package br.com.gabriellferreira.multicurrency.domain.usecase

import br.com.gabriellferreira.multicurrency.data.mapper.CurrencyMapper
import br.com.gabriellferreira.multicurrency.domain.model.Currency
import br.com.gabriellferreira.multicurrency.domain.repository.CurrencyRepository
import io.reactivex.Observable
import io.reactivex.Observer
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CurrencyUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val currencyMapper: CurrencyMapper
) : BaseUseCase() {

    companion object {
        const val FETCH_CURRENCY_RATES_INTERVAL_SECONDS: Long = 1
    }

    private var currencyBase: String = "EUR"

    fun changeCurrencyBase(code: String) {
        currencyBase = code
    }

    fun fetchCurrencyRates(observer: Observer<Currency>) {
        Observable
            .interval(FETCH_CURRENCY_RATES_INTERVAL_SECONDS, TimeUnit.SECONDS, subscribeScheduler)
            .flatMap {
                currencyRepository.fetchCurrencyRates(currencyBase)
            }
            .map {
                currencyMapper.map(it)
            }
            .subscribeOn(subscribeScheduler)
            .observeOn(observeScheduler)
            .subscribe(observer)
    }
}