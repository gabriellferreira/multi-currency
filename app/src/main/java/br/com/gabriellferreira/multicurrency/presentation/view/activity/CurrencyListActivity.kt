package br.com.gabriellferreira.multicurrency.presentation.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.gabriellferreira.multicurrency.R
import br.com.gabriellferreira.multicurrency.domain.model.Currency
import br.com.gabriellferreira.multicurrency.presentation.di.AppApplication
import br.com.gabriellferreira.multicurrency.presentation.di.ControllerModule
import br.com.gabriellferreira.multicurrency.presentation.util.extension.hide
import br.com.gabriellferreira.multicurrency.presentation.util.extension.show
import br.com.gabriellferreira.multicurrency.presentation.view.adapter.CurrencyAdapter
import br.com.gabriellferreira.multicurrency.presentation.view.viewmodel.CurrencyListViewModel
import kotlinx.android.synthetic.main.activity_currency.*
import java.util.*
import javax.inject.Inject

class CurrencyListActivity : AppCompatActivity() {

    @Inject
    lateinit var currencyListViewModel: CurrencyListViewModel

    private val adapter by lazy { CurrencyAdapter(currencyListViewModel) }

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

//        initToolbar(getString(R.string.currency_title))
//        initViews()
//        initListeners()
//        setupToolbar()
        setupCurrencyRecycler()
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        currencyListViewModel.start()
    }

    override fun onPause() {
        currencyListViewModel.disableCurrencyRatesPooling()
        super.onPause()
    }

    private fun initObservers() {
        currencyListViewModel.isDataLoading.observeForever {
            if (it == true) {
                currency_swipe?.isEnabled = false
                currency_progress?.show()
            } else {
                currency_swipe?.isEnabled = true
                currency_progress?.hide()
            }
        }

        currencyListViewModel.items.observe(this,
            Observer<LinkedList<Currency>> { t ->
                adapter.submitList(t)
                adapter.notifyDataSetChanged()
            })
    }

    private fun setupCurrencyRecycler() {
        currency_recycler?.layoutManager = LinearLayoutManager(this)
        currency_recycler?.adapter = adapter
    }
}