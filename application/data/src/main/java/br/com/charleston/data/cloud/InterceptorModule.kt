package br.com.charleston.data.cloud

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import javax.inject.Named
import javax.inject.Singleton

@Module
class InterceptorModule {

    @Provides
    @Singleton
    @Named(RESPONSE_INTERCEPTOR)
    fun provideInterceptorResponse(): Interceptor {
        return Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.header("Content-Type", "application/json; charset=utf-8")
            chain.proceed(requestBuilder.build())
        }
    }

    @Provides
    @Singleton
    @Named(USER_AGENT_INTERCEPTOR)
    fun provideUserAgentInterceptor(): Interceptor {
        return Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.addHeader("User-Agent", userAgent())
            chain.proceed(requestBuilder.build())
        }
    }

    private fun userAgent(): String {
        val version = "1.0.0"
        val osVersion = android.os.Build.VERSION.RELEASE
        val pack = " br.com.charleston.github"
        val device = android.os.Build.MODEL
        return "Charleston.Github/$version ($pack; OS Version:$osVersion; Android $device)"
    }
}