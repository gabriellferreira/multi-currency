package br.com.gabriellferreira.multicurrency.domain.usecase

import br.com.gabriellferreira.multicurrency.data.mapper.CurrencyMapper
import br.com.gabriellferreira.multicurrency.domain.model.Currency
import br.com.gabriellferreira.multicurrency.domain.repository.CurrencyRepository
import io.reactivex.Observable
import io.reactivex.Observer
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CurrencyUseCase @Inject constructor(private val currencyRepository: CurrencyRepository,
                                          private val currencyMapper: CurrencyMapper) : BaseUseCase() {
    
    private var currencyBase: String = "EUR"

    fun changeCurrencyBase(code: String) {
        currencyBase = code
    }

    fun fetchMostPopularCurrency(observer: Observer<Currency>) {
        Observable
                .interval(2, TimeUnit.SECONDS)
                .flatMap {
                    currencyRepository.fetchMostPopularNews(currencyBase)
                }
                .map {
                    currencyMapper.map(it)
                }
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribe(observer)
    }
}