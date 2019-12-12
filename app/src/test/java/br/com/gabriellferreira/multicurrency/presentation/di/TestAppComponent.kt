package br.com.gabriellferreira.multicurrency.presentation.di

import br.com.gabriellferreira.multicurrency.data.repository.NewsDataRepositoryTest
import br.com.gabriellferreira.multicurrency.presentation.di.scope.ApplicationScope
import br.com.gabriellferreira.multicurrency.presentation.view.presenter.NewsListPresenterTest
import dagger.Component

@ApplicationScope
@Component(modules = [AppModule::class, RepositoryModule::class, TestServiceModule::class])
interface TestAppComponent : AppComponent {

    fun inject(appApplication: NewsListPresenterTest)
    fun inject(appApplication: NewsDataRepositoryTest)
}