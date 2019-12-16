package br.com.gabriellferreira.multicurrency.data.network.service

import io.reactivex.Observable

class FakeNewsService : NewsService {

    private val nyTimesData = NYTimesResultData()

    override fun getMostPopularNews(period: Int): Observable<NYTimesResultData> =
            Observable.just(nyTimesData)
}