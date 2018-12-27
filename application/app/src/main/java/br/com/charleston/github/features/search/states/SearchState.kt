package br.com.charleston.github.features.search.states

import br.com.charleston.domain.model.GithubModel

sealed class SearchState {
    object Listening : SearchState()
    data class Loading(val searchingByText: String) : SearchState()
    data class Success(val data: List<GithubModel>) : SearchState()
    data class Error(val error: Throwable) : SearchState()
    object NoResult : SearchState()
}