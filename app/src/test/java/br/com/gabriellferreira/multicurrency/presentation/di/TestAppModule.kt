package br.com.gabriellferreira.multicurrency.presentation.di

import android.content.Context
import android.content.res.Resources
import br.com.gabriellferreira.multicurrency.presentation.util.connection.InternetConnectionVerifier
import com.nhaarman.mockitokotlin2.mock

class TestAppModule(
        appApplication: AppApplication
) : AppModule(appApplication) {

    override fun provideApplicationContext(): Context = mock()

    override fun provideInternetConnectionVerifier(context: Context): InternetConnectionVerifier = mock()

    override fun provideResources(context: Context): Resources = mock()
}