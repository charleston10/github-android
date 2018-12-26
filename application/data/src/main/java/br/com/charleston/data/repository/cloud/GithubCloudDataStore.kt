package br.com.charleston.data.repository.cloud

import br.com.charleston.data.cloud.requests.GithubApi
import br.com.charleston.data.cloud.responses.mapper.GithubCloudMapper
import br.com.charleston.domain.model.GithubModel
import io.reactivex.Observable

class GithubCloudDataStore(
    private val api: GithubApi,
    private val mapper: GithubCloudMapper
) : IGithubCloudDataStore {

    override fun getRepositories(name: String): Observable<List<GithubModel>> {
        return api.getRepositories(
            name = name,
            visibility = "public",
            type = "sources"
        ).map { list ->
            list.map { item -> mapper.transform(item) }
        }
    }
}