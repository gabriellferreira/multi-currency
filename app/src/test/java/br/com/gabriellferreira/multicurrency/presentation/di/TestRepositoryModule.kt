package br.com.gabriellferreira.multicurrency.presentation.di

import br.com.gabriellferreira.multicurrency.data.repository.NewsDataRepository
import br.com.gabriellferreira.multicurrency.domain.repository.NewsRepository
import com.nhaarman.mockitokotlin2.mock

class TestRepositoryModule : RepositoryModule() {

    override fun provideNewsRepository(repository: NewsDataRepository): NewsRepository = mock()
}