package br.com.gabriellferreira.multicurrency.domain.repository

import br.com.gabriellferreira.multicurrency.domain.model.Currency
import io.reactivex.Observable

interface CurrencyRepository {
    fun fetchMostPopularNews(baseCurrency: String): Observable<Currency>
}