package br.com.charleston.data.cloud.requests

import br.com.charleston.data.cloud.QUERY_REPO_NAME
import br.com.charleston.data.cloud.QUERY_REPO_TYPE
import br.com.charleston.data.cloud.QUERY_REPO_VISIBILITY
import br.com.charleston.data.cloud.responses.GithubResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("/repositories")
    fun getRepositories(
        @Query(QUERY_REPO_NAME) name: String,
        @Query(QUERY_REPO_VISIBILITY) visibility: String,
        @Query(QUERY_REPO_TYPE) type: String
    ): Observable<List<GithubResponse>>
}
