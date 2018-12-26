package br.com.charleston.data.cloud

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
open class UrlApiModule {

    @Provides
    @Named(URL_DOMAIN)
    open fun provideUrl(): String {
        return "https://api.github.com/"
    }
}