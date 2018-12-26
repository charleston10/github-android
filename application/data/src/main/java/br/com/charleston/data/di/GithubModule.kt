package br.com.charleston.data.di

import br.com.charleston.data.cloud.requests.GithubApi
import br.com.charleston.data.cloud.responses.mapper.GithubCloudMapper
import br.com.charleston.data.repository.GithubDataRepository
import br.com.charleston.data.repository.cloud.GithubCloudDataStore
import br.com.charleston.domain.repository.IGithubRepository
import dagger.Module
import dagger.Provides

@Module
class GithubModule {

    @Provides
    fun cloudMapper(): GithubCloudMapper {
        return GithubCloudMapper()
    }

    @Provides
    fun cloudDataStore(
        api: GithubApi,
        mapper: GithubCloudMapper
    ): GithubCloudDataStore {
        return GithubCloudDataStore(api, mapper)
    }

    @Provides
    fun dataRepository(
        cloud: GithubCloudDataStore
    ): IGithubRepository {
        return GithubDataRepository(cloud)
    }
}