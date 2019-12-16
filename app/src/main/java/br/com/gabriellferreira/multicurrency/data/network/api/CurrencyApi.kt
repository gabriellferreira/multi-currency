package br.com.gabriellferreira.multicurrency.data.network.api

import br.com.gabriellferreira.multicurrency.data.network.service.CurrencyService
import javax.inject.Inject

class CurrencyApi @Inject constructor() : BaseApi() {

    private val currencyService: CurrencyService

    init {
        val retrofit = build()
        currencyService = retrofit.create(CurrencyService::class.java)
    }

    fun fetchCurrencyRates(baseCurrency: String) = currencyService.fetchCurrencyRates(baseCurrency)
}