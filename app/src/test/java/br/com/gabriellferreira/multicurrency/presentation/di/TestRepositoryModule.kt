package br.com.gabriellferreira.multicurrency.presentation.di

import br.com.gabriellferreira.multicurrency.data.repository.CurrencyDataRepository
import br.com.gabriellferreira.multicurrency.domain.repository.CurrencyRepository
import com.nhaarman.mockitokotlin2.mock

class TestRepositoryModule : RepositoryModule() {

    override fun provideCurrencyRepository(repository: CurrencyDataRepository): CurrencyRepository = mock()
}