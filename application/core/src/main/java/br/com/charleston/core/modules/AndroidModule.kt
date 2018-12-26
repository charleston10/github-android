package br.com.charleston.core.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }
}