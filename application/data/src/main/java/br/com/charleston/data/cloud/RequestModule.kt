package br.com.charleston.data.cloud

import br.com.charleston.data.cloud.requests.GithubApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class RequestModule {

    @Provides
    fun provideApi(retrofit: Retrofit): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }
}