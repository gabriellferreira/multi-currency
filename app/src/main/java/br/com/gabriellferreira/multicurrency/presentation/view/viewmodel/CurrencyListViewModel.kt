package br.com.gabriellferreira.multicurrency.presentation.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gabriellferreira.multicurrency.domain.model.Currency
import br.com.gabriellferreira.multicurrency.domain.usecase.CurrencyUseCase
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.*
import javax.inject.Inject

class CurrencyListViewModel @Inject constructor(
    private val useCase: CurrencyUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _items =
        MutableLiveData<LinkedList<Currency>>().apply { value = LinkedList() }
    val items: LiveData<LinkedList<Currency>> = _items

    private val _isDataLoading = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> = _isDataLoading

    private val _updateItem = MutableLiveData<Int>()
    val updateItem: LiveData<Int> = _updateItem

    fun start() {
        loadCurrencyRates()
    }

    private fun loadCurrencyRates() {
        useCase.fetchCurrencyRates(object : Observer<Currency> {
            override fun onComplete() {
//                view?.showContent()
//                view?.hideLoading()
//                view?.onRefreshFinished()
            }

            override fun onNext(item: Currency) {
                addItem(item)
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

    private fun addItem(item: Currency) {
        val index = _items.value?.indexOfFirst {
            it.code == item.code
        } ?: -1

        println("addItem $index")

        if (index == 0) {
            return
        }

        if (index > 0) {
            _items.value?.set(index, item)
            _items.value = _items.value
        } else {
            _items.value?.add(item)
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun disableCurrencyRatesPooling() {
        compositeDisposable.clear()
    }

    fun setCurrencyAsBase(code: String, baseValue: Double) {
        useCase.changeCurrencyBase(code, baseValue)
        val currency = _items.value?.first {
            it.code == code
        }
        _items.value?.remove(currency)
        _items.value?.addFirst(currency)
    }
}