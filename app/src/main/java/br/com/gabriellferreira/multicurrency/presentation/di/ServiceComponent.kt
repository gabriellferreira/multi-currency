package br.com.gabriellferreira.multicurrency.presentation.di

import br.com.gabriellferreira.multicurrency.presentation.di.scope.ServiceScope
import dagger.Subcomponent

@ServiceScope
@Subcomponent(modules = [ServiceModule::class])
interface ServiceComponent