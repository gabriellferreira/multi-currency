package br.com.gabriellferreira.multicurrency.presentation.di

import br.com.gabriellferreira.multicurrency.data.repository.CurrencyDataRepositoryTest
import br.com.gabriellferreira.multicurrency.presentation.di.scope.ApplicationScope
import br.com.gabriellferreira.multicurrency.presentation.view.presenter.CurrencyListPresenterTest
import dagger.Component

@ApplicationScope
@Component(modules = [AppModule::class, RepositoryModule::class, TestServiceModule::class])
interface TestAppComponent : AppComponent {

    fun inject(appApplication: CurrencyListPresenterTest)
    fun inject(appApplication: CurrencyDataRepositoryTest)
}