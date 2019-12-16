package br.com.gabriellferreira.multicurrency.presentation.view

import br.com.gabriellferreira.multicurrency.domain.model.Currency

interface CurrencyListContract {

    interface View : BaseContract.View {
        fun initViews()
        fun setupToolbar()
        fun showLoading()
        fun hideLoading()
        fun showError()
        fun hideError()
        fun showContent()
        fun hideContent()
        fun onRefreshFinished()
        fun addCurrency(currency: Currency)
        fun clearAdapter()
        fun showEmptyView()
        fun hideEmptyView()
        fun scrollRecyclerTop()
        fun setCurrencyAsBase(code: String)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onInitialize()
        fun loadCurrencyRates()
        fun changeCurrencyBase(code: String)
        fun disableCurrencyRatesPooling()
    }
}
