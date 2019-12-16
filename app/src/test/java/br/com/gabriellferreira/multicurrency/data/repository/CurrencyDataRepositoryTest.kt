package br.com.gabriellferreira.multicurrency.data.repository

import br.com.gabriellferreira.multicurrency.data.model.CurrencyRatesData
import br.com.gabriellferreira.multicurrency.data.network.api.CurrencyApi
import br.com.gabriellferreira.multicurrency.presentation.di.*
import br.com.gabriellferreira.multicurrency.presentation.util.TrampolineSchedulerRule
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.greaterThanOrEqualTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CurrencyDataRepositoryTest {

    @get:Rule
    var schedulersOverrideRule: TrampolineSchedulerRule = TrampolineSchedulerRule()
    @Mock
    lateinit var currencyApi: CurrencyApi

    lateinit var repository: CurrencyDataRepository

    companion object {
        const val CURRENCY_EURO_CODE = "EUR"
        const val CURRENCY_BRAZIL_CODE = "BRL"
        const val CURRENCY_BRAZIL_RATE = 4.70
    }

    private val currencyRatesData = CurrencyRatesData(
        base = CURRENCY_EURO_CODE,
        date = "2019-12-12",
        rates = mapOf(
            Pair(CURRENCY_BRAZIL_CODE, CURRENCY_BRAZIL_RATE)
        )
    )

    @Suppress("DEPRECATION")
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        val component = DaggerTestAppComponent.builder()
            .appModule(TestAppModule(AppApplication()))
            .repositoryModule(TestRepositoryModule())
            .testServiceModule(TestServiceModule())
            .build()
        component.inject(this)
        repository = CurrencyDataRepository(currencyApi)

        whenever(currencyApi.fetchCurrencyRates(CURRENCY_EURO_CODE)).thenAnswer {
            Observable.just(currencyRatesData)
        }
    }

    @Test
    fun fetchCurrencyRates_validData_apiCall() {
        repository.fetchCurrencyRates(CURRENCY_EURO_CODE)
        verify(currencyApi).fetchCurrencyRates(CURRENCY_EURO_CODE)
    }

    @Test
    fun fetchCurrencyRates_validData_baseCurrencyAsFirstElement() {
        val result = repository.fetchCurrencyRates(CURRENCY_EURO_CODE).toList().blockingGet()
        verify(currencyApi).fetchCurrencyRates(CURRENCY_EURO_CODE)
        assertThat(result.size, greaterThanOrEqualTo(2))
        assertThat(result.first().name, `is`(CURRENCY_EURO_CODE))
    }

    @Test
    fun fetchCurrencyRates_validData_properMappingForFetchedCurrencies() {
        val result = repository.fetchCurrencyRates(CURRENCY_EURO_CODE).toList().blockingGet()
        verify(currencyApi).fetchCurrencyRates(CURRENCY_EURO_CODE)
        val item = result.first {
            it.name == "BRL"
        }

        assertThat(item.name, `is`(CURRENCY_BRAZIL_CODE))
        assertThat(item.rate, `is`(CURRENCY_BRAZIL_RATE))
    }
}