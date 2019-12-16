package br.com.gabriellferreira.multicurrency.presentation.view.presenter

import br.com.gabriellferreira.multicurrency.domain.model.Currency
import br.com.gabriellferreira.multicurrency.domain.usecase.CurrencyUseCase
import br.com.gabriellferreira.multicurrency.presentation.view.CurrencyListContract
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class CurrencyListPresenter @Inject constructor(private val latestCurrencyListUseCase: CurrencyUseCase) :
        BasePresenter<CurrencyListContract.View>(), CurrencyListContract.Presenter {

    override fun onInitialize() {
        view?.initViews()
        view?.setupToolbar()
    }

    override fun loadCurrencyRates() {
        latestCurrencyListUseCase.fetchCurrencyRates(object : Observer<Currency> {
            override fun onComplete() {
                view?.showContent()
                view?.hideLoading()
                view?.onRefreshFinished()
            }

            override fun onNext(t: Currency) {
                view?.addCurrency(t)
            }

            override fun onSubscribe(d: Disposable) {
//                view?.hideContent()
//                view?.showLoading()
                view?.hideError()
//                view?.clearAdapter()
                view?.hideEmptyView()
            }

            override fun onError(e: Throwable) {
                view?.hideContent()
                view?.hideLoading()
                view?.showError()
                view?.onRefreshFinished()
            }
        })
    }

    override fun changeCurrencyBase(code: String) {
        latestCurrencyListUseCase.changeCurrencyBase(code)
    }

    override fun onCurrencyClicked(currency: Currency) {
//        view?.redirectWeb(currency.url)
    }
}