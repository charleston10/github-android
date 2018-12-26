package br.com.charleston.data.cloud.requests

import br.com.charleston.data.cloud.QUERY_REPO_NAME
import br.com.charleston.data.cloud.QUERY_REPO_SORT
import br.com.charleston.data.cloud.responses.DataResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("/search/repositories")
    fun getRepositories(
        @Query(QUERY_REPO_NAME) name: String,
        @Query(QUERY_REPO_SORT) sort: String
    ): Observable<DataResponse>
}
