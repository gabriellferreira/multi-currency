package br.com.gabriellferreira.multicurrency.data.repository

import br.com.gabriellferreira.multicurrency.data.mapper.CurrencyMapper
import br.com.gabriellferreira.multicurrency.data.model.CurrencyData
import br.com.gabriellferreira.multicurrency.data.network.api.CurrencyApi
import br.com.gabriellferreira.multicurrency.domain.model.Currency
import br.com.gabriellferreira.multicurrency.domain.repository.CurrencyRepository
import io.reactivex.Observable
import javax.inject.Inject

class CurrencyDataRepository @Inject constructor(private val currencyApi: CurrencyApi,
                                                 private val mapper: CurrencyMapper) :
    CurrencyRepository {

    override fun fetchCurrencyRates(baseCurrency: String): Observable<List<Currency>> =
        currencyApi.fetchCurrencyRates(baseCurrency)
            .map { data ->
                val array = mutableListOf<CurrencyData>()
                array.add(
                    CurrencyData(
                        base = data.base,
                        name = data.base,
                        rate = 1.toDouble()
                    )
                )
                data.rates?.forEach {
                    array.add(
                        CurrencyData(
                            base = baseCurrency,
                            name = it.key,
                            rate = it.value
                        )
                    )
                }
                array
            }
            .map { list ->
                list.map { data ->
                    mapper.map(data)
                }
            }
}