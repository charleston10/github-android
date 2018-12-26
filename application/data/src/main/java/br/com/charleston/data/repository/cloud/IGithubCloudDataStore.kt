package br.com.charleston.data.repository.cloud

import br.com.charleston.domain.model.GithubModel
import io.reactivex.Observable

interface IGithubCloudDataStore {
    fun getRepositories(name: String): Observable<GithubModel>
}