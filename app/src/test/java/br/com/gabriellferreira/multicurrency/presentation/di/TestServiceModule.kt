package br.com.gabriellferreira.multicurrency.presentation.di

import br.com.gabriellferreira.multicurrency.data.network.service.CurrencyService
import br.com.gabriellferreira.multicurrency.data.network.service.FakeCurrencyService
import dagger.Module
import dagger.Provides

@Module
class TestServiceModule {

    @Provides
    fun provideFakeCurrencyService(): CurrencyService = FakeCurrencyService()
}
