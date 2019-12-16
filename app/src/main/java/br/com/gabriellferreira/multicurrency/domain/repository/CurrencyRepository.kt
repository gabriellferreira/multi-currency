package br.com.gabriellferreira.multicurrency.domain.repository

import br.com.gabriellferreira.multicurrency.data.model.CurrencyData
import br.com.gabriellferreira.multicurrency.domain.model.Currency
import io.reactivex.Observable

interface CurrencyRepository {
    fun fetchCurrencyRates(baseCurrency: String): Observable<CurrencyData>
}