package br.com.gabriellferreira.multicurrency.presentation.view.viewmodel

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import br.com.gabriellferreira.multicurrency.domain.model.Currency
import br.com.gabriellferreira.multicurrency.domain.usecase.CurrencyUseCase
import br.com.gabriellferreira.multicurrency.presentation.util.extension.parse
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.*
import javax.inject.Inject

class CurrencyListViewModel @Inject constructor(
    private val useCase: CurrencyUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    var _items: MutableLiveData<LinkedList<Currency>> =
        MutableLiveData<LinkedList<Currency>>().apply { value = LinkedList() }
    var items: LiveData<LinkedList<Currency>> = Transformations.distinctUntilChanged(_items)

    val _isDataLoading = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> = _isDataLoading

    val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            useCase.changeBaseValue(Double.parse(s.toString()))
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    }

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
                _items.value = LinkedList(data)
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
}