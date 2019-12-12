package br.com.gabriellferreira.multicurrency.domain.usecase

import br.com.gabriellferreira.multicurrency.data.mapper.CurrencyMapper
import br.com.gabriellferreira.multicurrency.domain.model.Currency
import br.com.gabriellferreira.multicurrency.domain.repository.CurrencyRepository
import io.reactivex.Observer
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CurrencyUseCase @Inject constructor(private val currencyRepository: CurrencyRepository,
                                          private val currencyMapper: CurrencyMapper) : BaseUseCase() {

    fun fetchMostPopularCurrency(currencyBase: String, observer: Observer<Currency>) {
        currencyRepository.fetchMostPopularNews(currencyBase)
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .repeatWhen { completed -> completed.delay(2, TimeUnit.SECONDS) }
                .subscribe(observer)
    }
}