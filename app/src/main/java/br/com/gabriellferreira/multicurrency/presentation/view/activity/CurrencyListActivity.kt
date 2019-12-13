package br.com.gabriellferreira.multicurrency.presentation.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import br.com.gabriellferreira.multicurrency.R
import br.com.gabriellferreira.multicurrency.domain.model.Currency
import br.com.gabriellferreira.multicurrency.presentation.util.extension.hide
import br.com.gabriellferreira.multicurrency.presentation.util.extension.show
import br.com.gabriellferreira.multicurrency.presentation.view.CurrencyListContract
import br.com.gabriellferreira.multicurrency.presentation.view.adapter.CurrencyAdapter
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_currency.*
import kotlinx.android.synthetic.main.include_toolbar.*

class CurrencyListActivity : BaseActivity<CurrencyListContract.Presenter, CurrencyListContract.View>(),
        CurrencyListContract.View {

    private val currencyListAdapter by lazy { CurrencyAdapter(view = this) }
    private var currencyClicksDisposable: Disposable? = null

    override fun createPresenter(): CurrencyListContract.Presenter {
        getControllerComponent().inject(this)
        return getControllerComponent().currencyPresenter()
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, CurrencyListActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)
        initToolbar(getString(R.string.currency_title))
        presenter?.onInitialize()
        presenter?.loadCurrencyRates()
    }

    override fun initViews() {
        initListeners()
        setupCurrencyRecycler()
    }

    private fun initListeners() {
        currency_swipe?.isEnabled = false
        currency_swipe?.setOnRefreshListener {
            presenter?.loadCurrencyRates()
        }
    }

    override fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun setupCurrencyRecycler() {
        currency_recycler?.layoutManager = LinearLayoutManager(this)
        currency_recycler?.adapter = currencyListAdapter
        currencyClicksDisposable = currencyListAdapter.onItemClickSubject.subscribe {
            presenter?.onCurrencyClicked(it)
        }
    }

    override fun scrollRecyclerTop() {
        currency_recycler?.scrollToPosition(0)
    }

    override fun addCurrency(currency: Currency) {
        if (currency_recycler?.scrollState == SCROLL_STATE_IDLE) {
            currencyListAdapter.add(currency)
        }
    }

    override fun setCurrencyAsBase(code: String) {
        presenter?.changeCurrencyBase(code)
    }

    override fun clearAdapter() {
        currencyListAdapter.clear()
    }

    override fun showLoading() {
        currency_swipe?.isEnabled = false
        currency_progress?.show()
    }

    override fun hideLoading() {
        currency_swipe?.isEnabled = true
        currency_progress?.hide()
    }

    override fun showError() {
        currency_error_card?.show()
    }

    override fun hideError() {
        currency_error_card?.hide()
    }

    override fun showContent() {
        currency_recycler?.show()
    }

    override fun hideContent() {
        currency_recycler?.hide()
    }

    override fun showEmptyView() {
        currency_empty_card?.show()
    }

    override fun hideEmptyView() {
        currency_empty_card?.hide()
    }

    override fun onRefreshFinished() {
        currency_swipe?.isRefreshing = false
    }
}