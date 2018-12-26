package br.com.charleston.data.cloud

import android.annotation.SuppressLint
import br.com.charleston.data.cloud.RESPONSE_INTERCEPTOR
import br.com.charleston.data.cloud.URL_DOMAIN
import br.com.charleston.data.cloud.USER_AGENT_INTERCEPTOR
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
class NetworkModule {

    @Provides
    fun okHttpClient(
        @Named(USER_AGENT_INTERCEPTOR) userAgentInterceptor: Interceptor,
        @Named(RESPONSE_INTERCEPTOR) responseInterceptor: Interceptor,
        trustManager: X509TrustManager,
        sslSocketFactory: SSLSocketFactory
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(90, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(userAgentInterceptor)
            .addInterceptor(responseInterceptor)
            .sslSocketFactory(sslSocketFactory, trustManager)
            .build()
    }

    @Provides
    fun retrofitBuilder(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    @Provides
    fun retrofit(
        @Named(URL_DOMAIN) urlDomain: String,
        retrofitBuilder: Retrofit.Builder
    ): Retrofit {
        return retrofitBuilder
            .baseUrl(urlDomain)
            .build()
    }

    @Provides
    fun gsonFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().create())
    }

    /**
     * SSL Enable for github read *.txt
     * @see (https://futurestud.io/tutorials/retrofit-2-how-to-trust-unsafe-ssl-certificates-self-signed-expired)
     */
    @Provides
    @Singleton
    fun provideSSLSocketFactory(trustManager: X509TrustManager): SSLSocketFactory {
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, arrayOf<TrustManager>(trustManager), java.security.SecureRandom())
        return sslContext.socketFactory
    }

    @Provides
    @Singleton
    @SuppressLint("TrustAllX509TrustManager")
    fun provideX509TrustManager(): X509TrustManager {
        return object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }
    }
}