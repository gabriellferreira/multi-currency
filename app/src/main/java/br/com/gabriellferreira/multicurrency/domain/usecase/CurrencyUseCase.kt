package br.com.gabriellferreira.multicurrency.domain.usecase

import android.text.Editable
import android.text.TextWatcher
import br.com.gabriellferreira.multicurrency.data.mapper.CurrencyMapper
import br.com.gabriellferreira.multicurrency.domain.model.Currency
import br.com.gabriellferreira.multicurrency.domain.repository.CurrencyRepository
import br.com.gabriellferreira.multicurrency.presentation.util.extension.parse
import io.reactivex.Observable
import io.reactivex.Observer
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CurrencyUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val currencyMapper: CurrencyMapper
) : BaseUseCase() {

    companion object {
        const val FETCH_CURRENCY_RATES_INTERVAL_SECONDS: Long = 1
    }

    private var currencyBase: String = "EUR"
    private var baseValue: Double = 1.0
    private var currencyList = listOf<Currency>()

    val currencyTextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            changeBaseValue(Double.parse(s.toString()))
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    }

    fun changeCurrencyBase(code: String) {
        currencyBase = code

        val tempList = currencyList.toMutableList()
        val firstItem = tempList.first {
            it.code == code
        }
        tempList.remove(firstItem)

        currencyList = mutableListOf<Currency>().apply {
            add(firstItem)
            addAll(tempList)
        }
    }

    fun changeBaseValue(value: Double) {
        baseValue = value
    }

    fun fetchCurrencyRates(observer: Observer<List<Currency>>) {
        Observable
            .interval(FETCH_CURRENCY_RATES_INTERVAL_SECONDS, TimeUnit.SECONDS, subscribeScheduler)
            .flatMap {
                currencyRepository.fetchCurrencyRates(currencyBase)
            }
            .map { list ->
                list.map { data ->
                    currencyMapper.map(data, baseValue)
                }
            }
            .map { newList ->
                currencyList = if (currencyList.isNullOrEmpty()) {
                    newList
                } else {
                    mutableListOf<Currency>().apply {
                        currencyList.forEach { item ->
                            val tempItem = newList.first { it.code == item.code }
                            tempItem.let {
                                this.add(tempItem)
                            }
                        }
                    }
                }
                currencyList
            }
            .subscribeOn(subscribeScheduler)
            .observeOn(observeScheduler)
            .subscribe(observer)
    }
}