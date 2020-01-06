package br.com.gabriellferreira.multicurrency.presentation.view.viewmodel

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gabriellferreira.multicurrency.domain.model.Currency
import br.com.gabriellferreira.multicurrency.domain.usecase.CurrencyUseCase
import br.com.gabriellferreira.multicurrency.presentation.util.extension.parse
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

                val tempList = _items.value

                _items.value = if (tempList.isNullOrEmpty()) {
                    data
                } else {
                    mutableListOf<Currency>().apply {
                        tempList.forEach { item ->
                            val tempItem = data.first { it.code == item.code }
                            tempItem.let {
                                this.add(tempItem)
                            }
                        }
                    }
                }

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

        val tempList = _items.value?.toMutableList() ?: mutableListOf()
        val firstItem = tempList.first {
            it.code == code
        }
        tempList.remove(firstItem)


        _items.value = mutableListOf<Currency>().apply {
            add(firstItem)
            addAll(tempList)
        }

        useCase.changeCurrencyBase(code)
        useCase.changeBaseValue(baseValue)
    }
}