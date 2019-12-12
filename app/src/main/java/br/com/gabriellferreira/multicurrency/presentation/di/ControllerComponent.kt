package br.com.gabriellferreira.multicurrency.presentation.di

import br.com.gabriellferreira.multicurrency.presentation.di.scope.ControllerScope
import br.com.gabriellferreira.multicurrency.presentation.view.CurrencyListContract
import br.com.gabriellferreira.multicurrency.presentation.view.activity.CurrencyListActivity
import br.com.gabriellferreira.multicurrency.presentation.view.activity.MainActivity
import br.com.gabriellferreira.multicurrency.presentation.view.activity.NewsActivity
import br.com.gabriellferreira.multicurrency.presentation.view.presenter.CurrencyListPresenter
import br.com.gabriellferreira.multicurrency.presentation.view.presenter.MainPresenter
import br.com.gabriellferreira.multicurrency.presentation.view.presenter.NewsListPresenter
import dagger.Subcomponent

@ControllerScope
@Subcomponent(modules = [ControllerModule::class])
interface ControllerComponent {

    // Presenter
    fun mainPresenter(): MainPresenter

    fun newsPresenter(): NewsListPresenter

    fun currencyPresenter(): CurrencyListPresenter

    // View
    fun inject(mainActivity: MainActivity)

    fun inject(newsActivity: NewsActivity)

    fun inject(currencyListActivity: CurrencyListActivity)
}