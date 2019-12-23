package br.com.gabriellferreira.multicurrency.presentation.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gabriellferreira.multicurrency.domain.model.Currency
import br.com.gabriellferreira.multicurrency.domain.usecase.CurrencyUseCase
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.*
import javax.inject.Inject

class CurrencyListViewModel @Inject constructor(
    private val useCase: CurrencyUseCase
) : ViewModel() {

    private val _items =
        MutableLiveData<LinkedList<java.util.Currency>>().apply { value = LinkedList() }
    val items: LiveData<LinkedList<java.util.Currency>> = _items

    private val _isDataLoading = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> = _isDataLoading


    fun start() {
        loadCurrencyRates()
    }

    private fun loadCurrencyRates() {
        useCase.fetchCurrencyRates(object : Observer<Currency> {
            override fun onComplete() {
//                view?.showContent()
//                view?.hideLoading()
//                view?.onRefreshFinished()
//
                _isDataLoading.value = false
            }

            override fun onNext(t: Currency) {
//                view?.addCurrency(t)
            }

            override fun onSubscribe(d: Disposable) {
                _isDataLoading.value = true
            }

            override fun onError(e: Throwable) {
                _isDataLoading.value = false
            }
        })
    }
//
//    override fun changeCurrencyBase(code: String) {
//        latestCurrencyListUseCase.changeCurrencyBase(code)
//    }
}