package br.com.gabriellferreira.multicurrency.presentation.view.viewmodel

import android.text.TextWatcher
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gabriellferreira.multicurrency.domain.model.Currency
import br.com.gabriellferreira.multicurrency.domain.usecase.CurrencyUseCase
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class CurrencyListViewModel @Inject constructor(
    private val useCase: CurrencyUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    var _items: MutableLiveData<List<Currency>> = MutableLiveData()
    var items: LiveData<List<Currency>> = _items

    val _isDataLoading = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> = _isDataLoading

    fun start() {
        loadCurrencyRates()
    }

    private val subscribeScheduler: Scheduler = Schedulers.io()

    private val observeScheduler: Scheduler = AndroidSchedulers.mainThread()

    private fun loadCurrencyRates() {
        compositeDisposable.add(
            useCase.fetchCurrencyRates()
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribe(this::postCurrencyList, this::onFetchError)
        )
    }

    private fun postCurrencyList(list: List<Currency>) {
        _items.postValue(list)
        if (_isDataLoading.value == true) {
            _isDataLoading.postValue(false)
        }
    }

    private fun onFetchError(error: Throwable) {
        _isDataLoading.postValue(false)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun disableCurrencyRatesPooling() {
        compositeDisposable.clear()
    }

    fun setCurrencyAsBase(code: String, baseValue: Double) {
        useCase.changeCurrencyBase(code)
        useCase.changeBaseValue(baseValue)
    }

    fun getCurrencyTextWatcher(): TextWatcher = useCase.currencyTextWatcher
}