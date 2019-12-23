package br.com.gabriellferreira.multicurrency.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.gabriellferreira.multicurrency.domain.usecase.CurrencyUseCase
import br.com.gabriellferreira.multicurrency.presentation.view.viewmodel.CurrencyListViewModel
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Inject
import kotlin.reflect.KClass

@Module
class ViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(CurrencyListViewModel::class)
    fun getCurrencyListViewModel(useCase: CurrencyUseCase): ViewModel {
        return CurrencyListViewModel(useCase)
    }

    @Provides
    fun getViewModelFactory(
        map: Map<Class<out ViewModel>,
                @JvmSuppressWildcards ViewModel>
    ): ViewModelProvider.Factory {
        return ViewModelFactory(map)
    }
}

class ViewModelFactory @Inject constructor(val viewModelMap: Map<Class<out ViewModel>, ViewModel>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModelMap[modelClass] as T
    }
}

@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ViewModelKey(val value: KClass<out ViewModel>)