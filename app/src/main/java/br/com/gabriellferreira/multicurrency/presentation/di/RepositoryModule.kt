package br.com.gabriellferreira.multicurrency.presentation.di

import br.com.gabriellferreira.multicurrency.data.repository.CurrencyDataRepository
import br.com.gabriellferreira.multicurrency.domain.repository.CurrencyRepository
import br.com.gabriellferreira.multicurrency.presentation.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
open class RepositoryModule {

    @Provides
    @ApplicationScope
    open fun provideCurrencyRepository(repository: CurrencyDataRepository): CurrencyRepository =
        repository
}