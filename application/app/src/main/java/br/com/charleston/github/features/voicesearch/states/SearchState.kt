package br.com.charleston.github.features.voicesearch.states

sealed class SearchState {
    object Listening : SearchState()
    data class Success(val data: List<SearchState>) : SearchState()
    data class Error(val error: Throwable) : SearchState()
    data class Loading(val searchingByText: String) : SearchState()
}