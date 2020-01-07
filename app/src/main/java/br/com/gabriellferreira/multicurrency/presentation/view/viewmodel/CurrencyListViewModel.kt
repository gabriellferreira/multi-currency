package br.com.gabriellferreira.multicurrency.presentation.view.viewmodel

import android.text.TextWatcher
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gabriellferreira.multicurrency.domain.model.Currency
import br.com.gabriellferreira.multicurrency.domain.usecase.CurrencyUseCase
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class CurrencyListViewModel @Inject constructor(
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

    private fun loadCurrencyRates() {

        useCase.fetchCurrencyRates(object : Observer<List<Currency>> {
            override fun onComplete() {
//                view?.showContent()
//                view?.hideLoading()
//                view?.onRefreshFinished()
            }

            override fun onNext(data: List<Currency>) {
                _items.value = data
                if (_isDataLoading.value == true) {
                    _isDataLoading.value = false
                }
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
                _isDataLoading.value = true
            }

            override fun onError(e: Throwable) {
                _isDataLoading.value = false
            }
        })
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