package br.com.gabriellferreira.multicurrency.data.network.service

import br.com.gabriellferreira.multicurrency.data.model.CurrencyRatesData
import io.reactivex.Observable

class FakeCurrencyService : CurrencyService {

    private val currencyRatesData = CurrencyRatesData()

    override fun fetchCurrencyRates(baseCurrency: String): Observable<CurrencyRatesData> =
        Observable.just(currencyRatesData)
}