package br.com.charleston.domain.repository

import br.com.charleston.domain.model.GithubModel
import io.reactivex.Observable

interface IGithubRepository {
    fun getRepositories(name: String): Observable<GithubModel>
}