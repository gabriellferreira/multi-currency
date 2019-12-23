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

            override fun onNext(list: List<Currency>) {
                _items.value = LinkedList(list)
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
}