package br.com.gabriellferreira.multicurrency.presentation.view.presenter

import br.com.gabriellferreira.multicurrency.presentation.view.MainContract
import javax.inject.Inject

class MainPresenter @Inject constructor() : BasePresenter<MainContract.View>(),
    MainContract.Presenter {

    override fun onCurrencyRatesClick() {
        view?.redirectMultiCurrency()
    }
}