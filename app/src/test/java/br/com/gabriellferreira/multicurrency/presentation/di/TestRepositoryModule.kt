package br.com.gabriellferreira.multicurrency.presentation.di

import com.nhaarman.mockitokotlin2.mock

class TestRepositoryModule : RepositoryModule() {

    override fun provideNewsRepository(repository: NewsDataRepository): NewsRepository = mock()
}