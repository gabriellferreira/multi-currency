package br.com.gabriellferreira.multicurrency.data.repository

import br.com.gabriellferreira.multicurrency.data.model.CurrencyData
import br.com.gabriellferreira.multicurrency.data.network.api.CurrencyApi
import br.com.gabriellferreira.multicurrency.domain.repository.CurrencyRepository
import io.reactivex.Observable
import javax.inject.Inject

class CurrencyDataRepository @Inject constructor(private val currencyApi: CurrencyApi) : CurrencyRepository {

    override fun fetchMostPopularNews(baseCurrency: String): Observable<CurrencyData> =
            currencyApi.getLatestCurrencyRates(baseCurrency)
                    .map { data ->
                        val array = mutableListOf<CurrencyData>()
                        array.add(CurrencyData(base = data.base,
                                name = data.base,
                                rate = 1.toDouble()))
                        data.rates?.forEach {
                            array.add(CurrencyData(base = baseCurrency,
                                    name = it.key,
                                    rate = it.value))
                        }
                        array
                    }
                    .flatMapIterable {
                        it
                    }
}