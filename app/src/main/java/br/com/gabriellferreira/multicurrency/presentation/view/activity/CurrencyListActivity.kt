package br.com.gabriellferreira.multicurrency.presentation.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import br.com.gabriellferreira.multicurrency.R
import br.com.gabriellferreira.multicurrency.presentation.di.AppApplication
import br.com.gabriellferreira.multicurrency.presentation.di.ControllerModule
import br.com.gabriellferreira.multicurrency.presentation.di.ViewModelFactory
import br.com.gabriellferreira.multicurrency.presentation.util.extension.hide
import br.com.gabriellferreira.multicurrency.presentation.util.extension.show
import br.com.gabriellferreira.multicurrency.presentation.view.viewmodel.CurrencyListViewModel
import kotlinx.android.synthetic.main.activity_currency.*
import javax.inject.Inject

class CurrencyListActivity : AppCompatActivity() {

//    private val currencyListAdapter by lazy { CurrencyAdapter() }

//    @Inject
//    lateinit var viewModelFactory: ViewModelFactory

//    @Inject
//    lateinit var currencyListViewModel: CurrencyListViewModel


    private val mControllerComponent by lazy {
        (application as AppApplication).getApplicationComponent()
            .newControllerComponent(ControllerModule(this))
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, CurrencyListActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)
        mControllerComponent.inject(this)

//        currencyListViewModel = ViewModelProviders.of(this, viewModelFactory)
//            .get(CurrencyListViewModel::class.java)
//        initToolbar(getString(R.string.currency_title))
//        initViews()
//        initListeners()
//        setupToolbar()

//        currencyListViewModel.isDataLoading.observeForever {
//            if (it == true) {
//                currency_swipe?.isEnabled = false
//                currency_progress?.show()
//            } else {
//                currency_swipe?.isEnabled = true
//                currency_progress?.hide()
//            }
//        }
    }

//    private fun initViews() {
//        initListeners()
//        setupCurrencyRecycler()
//    }

//    private fun initListeners() {
//        currency_swipe?.isEnabled = false
//        currency_swipe?.setOnRefreshListener {
//            presenter?.loadCurrencyRates()
//        }
//    }
//
//    private fun setupToolbar() {
//        setSupportActionBar(toolbar)
//    }
//
//    private fun setupCurrencyRecycler() {
//        currency_recycler?.layoutManager = LinearLayoutManager(this)
//        currency_recycler?.adapter = currencyListAdapter
//    }
//
//    override fun scrollRecyclerTop() {
//        currency_recycler?.scrollToPosition(0)
//    }
//
//    override fun addCurrency(currency: Currency) {
//        if (currency_recycler?.scrollState == SCROLL_STATE_IDLE) {
//            currencyListAdapter.add(currency)
//        }
//    }
//
//    override fun setCurrencyAsBase(code: String) {
//        presenter?.changeCurrencyBase(code)
//    }
//
//    override fun clearAdapter() {
//        currencyListAdapter.clear()
//    }
//
//    override fun showLoading() {
//        currency_swipe?.isEnabled = false
//        currency_progress?.show()
//    }
//
//    override fun hideLoading() {
//        currency_swipe?.isEnabled = true
//        currency_progress?.hide()
//    }
//
//    override fun showError() {
//        currency_error_card?.show()
//    }
//
//    override fun hideError() {
//        currency_error_card?.hide()
//    }
//
//    override fun showContent() {
//        currency_recycler?.show()
//    }
//
//    override fun hideContent() {
//        currency_recycler?.hide()
//    }
//
//    override fun showEmptyView() {
//        currency_empty_card?.show()
//    }
//
//    override fun hideEmptyView() {
//        currency_empty_card?.hide()
//    }
//
//    override fun onRefreshFinished() {
//        currency_swipe?.isRefreshing = false
//    }
}