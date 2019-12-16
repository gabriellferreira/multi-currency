package br.com.gabriellferreira.multicurrency.presentation.di

import android.content.Context
import android.content.res.Resources
import br.com.gabriellferreira.multicurrency.presentation.util.connection.DefaultInternetConnectionVerifier
import br.com.gabriellferreira.multicurrency.presentation.util.connection.InternetConnectionVerifier
import dagger.Module
import dagger.Provides

@Module
open class AppModule(private val appApplication: AppApplication) {

    @Provides
    open fun provideApplicationContext(): Context = appApplication

    @Provides
    open fun provideResources(context: Context): Resources = context.resources

    @Provides
    open fun provideInternetConnectionVerifier(context: Context): InternetConnectionVerifier =
        DefaultInternetConnectionVerifier(context)
}