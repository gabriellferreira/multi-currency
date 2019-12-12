package br.com.gabriellferreira.multicurrency.domain.repository

import br.com.gabriellferreira.multicurrency.data.model.NewsData
import io.reactivex.Observable

interface NewsRepository {
    fun fetchMostPopularNews(period: Int): Observable<NewsData>
}