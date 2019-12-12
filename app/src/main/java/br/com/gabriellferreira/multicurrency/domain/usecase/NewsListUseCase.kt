package br.com.gabriellferreira.multicurrency.domain.usecase

import br.com.gabriellferreira.multicurrency.data.mapper.NewsMapper
import br.com.gabriellferreira.multicurrency.domain.model.News
import br.com.gabriellferreira.multicurrency.domain.repository.NewsRepository
import io.reactivex.Observer
import javax.inject.Inject

class NewsListUseCase @Inject constructor(private val newsRepository: NewsRepository,
                                          private val newsMapper: NewsMapper) : BaseUseCase() {

    fun fetchMostPopularNews(timePeriod: Int, observer: Observer<News>) {
        newsRepository.fetchMostPopularNews(timePeriod)
                .map {
                    newsMapper.map(it)
                }
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribe(observer)
    }
}