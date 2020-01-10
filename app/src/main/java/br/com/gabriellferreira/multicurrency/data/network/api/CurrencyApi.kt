package br.com.gabriellferreira.multicurrency.data.network.api

import br.com.gabriellferreira.multicurrency.data.network.service.CurrencyService
import javax.inject.Inject

class CurrencyApi @Inject constructor(
    private val currencyService: CurrencyService
) : BaseApi() {

    fun fetchCurrencyRates(baseCurrency: String) = currencyService.fetchCurrencyRates(baseCurrency)
}