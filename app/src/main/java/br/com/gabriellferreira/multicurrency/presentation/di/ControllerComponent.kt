package br.com.gabriellferreira.multicurrency.presentation.di

import br.com.gabriellferreira.multicurrency.presentation.di.scope.ControllerScope
import br.com.gabriellferreira.multicurrency.presentation.view.activity.CurrencyListActivity
import br.com.gabriellferreira.multicurrency.presentation.view.activity.MainActivity
import br.com.gabriellferreira.multicurrency.presentation.view.presenter.CurrencyListPresenter
import br.com.gabriellferreira.multicurrency.presentation.view.presenter.MainPresenter
import dagger.Subcomponent

@ControllerScope
@Subcomponent(modules = [ControllerModule::class])
interface ControllerComponent {

    // Presenter
    fun mainPresenter(): MainPresenter

    fun currencyPresenter(): CurrencyListPresenter

    // View
    fun inject(mainActivity: MainActivity)

    fun inject(currencyListActivity: CurrencyListActivity)
}