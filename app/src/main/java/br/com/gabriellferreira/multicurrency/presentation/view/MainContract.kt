package br.com.gabriellferreira.multicurrency.presentation.view

interface MainContract {

    interface View : BaseContract.View {
        fun redirectLatestNews()
        fun redirectMultiCurrency()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onNewsButtonClick()
    }
}
