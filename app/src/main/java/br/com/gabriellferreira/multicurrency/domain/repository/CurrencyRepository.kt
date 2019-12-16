package br.com.gabriellferreira.multicurrency.domain.repository

import br.com.gabriellferreira.multicurrency.data.model.CurrencyData
import io.reactivex.Observable

interface CurrencyRepository {
    fun fetchCurrencyRates(baseCurrency: String): Observable<CurrencyData>
}