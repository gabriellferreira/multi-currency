package br.com.gabriellferreira.multicurrency.presentation.di

import br.com.gabriellferreira.multicurrency.data.network.service.FakeNewsService
import br.com.gabriellferreira.multicurrency.data.network.service.NewsService
import dagger.Module
import dagger.Provides

@Module
class TestServiceModule {

    @Provides
    fun provideFakeNewsService(): NewsService = FakeNewsService()
}