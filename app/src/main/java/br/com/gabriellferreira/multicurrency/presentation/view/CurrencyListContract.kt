package br.com.gabriellferreira.multicurrency.presentation.view

import android.view.MenuItem
import br.com.gabriellferreira.multicurrency.domain.model.Currency
import java.util.*

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
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onInitialize()
        fun onCurrencyClicked(currency: Currency)
        fun loadCurrencyRates()
    }

    @Suppress("MagicNumber")
    enum class TimePeriod(val days: Int) {
        ONE_DAY(1),
        SEVEN_DAYS(7),
        THIRTY_DAYS (30)
    }
}
