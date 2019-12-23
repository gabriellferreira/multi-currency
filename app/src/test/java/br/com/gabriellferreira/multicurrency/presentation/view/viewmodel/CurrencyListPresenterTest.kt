package br.com.gabriellferreira.multicurrency.presentation.view.viewmodel

import br.com.gabriellferreira.multicurrency.data.model.CurrencyData
import br.com.gabriellferreira.multicurrency.data.model.GenericException
import br.com.gabriellferreira.multicurrency.domain.repository.CurrencyRepository
import br.com.gabriellferreira.multicurrency.presentation.di.AppApplication
import br.com.gabriellferreira.multicurrency.presentation.di.DaggerTestAppComponent
import br.com.gabriellferreira.multicurrency.presentation.di.TestAppModule
import br.com.gabriellferreira.multicurrency.presentation.di.TestRepositoryModule
import br.com.gabriellferreira.multicurrency.presentation.util.TrampolineSchedulerRule
import br.com.gabriellferreira.multicurrency.presentation.view.CurrencyListContract
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject


@RunWith(RobolectricTestRunner::class)
class CurrencyListPresenterTest {

    @get:Rule
    var schedulersOverrideRule: TrampolineSchedulerRule = TrampolineSchedulerRule()
    @Inject
    lateinit var presenter: CurrencyListPresenter
    @Inject
    lateinit var currencyRepository: CurrencyRepository
    @Mock
    lateinit var view: CurrencyListContract.View

    @Suppress("DEPRECATION")
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        val component = DaggerTestAppComponent.builder()
            .appModule(TestAppModule(AppApplication()))
            .repositoryModule(TestRepositoryModule())
            .build()
        component.inject(this)
        presenter.attachView(view)
        successScenario()
    }

    //on init presenter
    @Test
    fun onInitialize_validData_presenterInitialization() {
        presenter.onInitialize()
        verify(view).initViews()
        verify(view).setupToolbar()
    }

    private fun successScenario() {
        whenever(currencyRepository.fetchCurrencyRates(any())).thenReturn(
            Observable.just(
                CurrencyData(
                    base = "EUR",
                    rate = 1.00,
                    name = "EUR"
                ),
                CurrencyData(
                    base = "EUR",
                    rate = 4.70,
                    name = "BRL"
                ),
                CurrencyData(
                    base = "EUR",
                    rate = 1.16,
                    name = "USD"
                )
            )
        )
    }

    private fun errorScenario() {
        whenever(currencyRepository.fetchCurrencyRates(any())).thenReturn(
            Observable.error(GenericException())
        )
    }
}