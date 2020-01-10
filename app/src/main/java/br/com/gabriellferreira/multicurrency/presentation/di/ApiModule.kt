package br.com.gabriellferreira.multicurrency.presentation.di

import br.com.gabriellferreira.multicurrency.data.network.api.BaseApi
import br.com.gabriellferreira.multicurrency.data.network.service.CurrencyService
import br.com.gabriellferreira.multicurrency.presentation.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ApiModule {

    @Provides
    @ApplicationScope
    fun provideCurrencyService(baseApi: BaseApi): CurrencyService {
        return baseApi.build().create(CurrencyService::class.java)
    }
}