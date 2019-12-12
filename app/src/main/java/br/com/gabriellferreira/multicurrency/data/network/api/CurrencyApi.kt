package br.com.gabriellferreira.multicurrency.data.network.api

import br.com.gabriellferreira.multicurrency.data.model.NewsData
import br.com.gabriellferreira.multicurrency.data.network.service.CurrencyService
import br.com.gabriellferreira.multicurrency.data.network.service.NewsService
import io.reactivex.Observable
import javax.inject.Inject

class CurrencyApi @Inject constructor() : BaseApi() {

    private val currencyService: CurrencyService

    init {
        val retrofit = build()
        currencyService = retrofit.create(CurrencyService::class.java)
    }

    fun getLatestCurrencyRates(baseCurrency: String) = currencyService.getMostPopularNews(baseCurrency)
}