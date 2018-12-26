package br.com.charleston.data.repository

import br.com.charleston.data.repository.cloud.GithubCloudDataStore
import br.com.charleston.domain.model.GithubModel
import br.com.charleston.domain.repository.IGithubRepository
import io.reactivex.Observable

class GithubDataRepository(
    private val cloudDataStore: GithubCloudDataStore
) : IGithubRepository {

    override fun getRepositories(name: String): Observable<GithubModel> {
        return cloudDataStore.getRepositories(name)
    }
}