package br.com.gabriellferreira.multicurrency.data.network.service

import br.com.gabriellferreira.multicurrency.data.model.CurrencyRatesData
import br.com.gabriellferreira.multicurrency.data.model.NYTimesResultData
import br.com.gabriellferreira.multicurrency.domain.model.Currency
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyService {

    @GET("latest")
    fun getMostPopularNews(@Query("base") baseCurrency: String): Observable<CurrencyRatesData>
}