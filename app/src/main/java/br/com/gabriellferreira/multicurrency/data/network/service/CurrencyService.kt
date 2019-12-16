package br.com.gabriellferreira.multicurrency.data.network.service

import br.com.gabriellferreira.multicurrency.data.model.CurrencyRatesData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {

    @GET("latest")
    fun fetchCurrencyRates(@Query("base") baseCurrency: String): Observable<CurrencyRatesData>
}