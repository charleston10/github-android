package br.com.charleston.github.features.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.charleston.core.base.BaseViewModel
import br.com.charleston.domain.DefaultObserver
import br.com.charleston.domain.interactor.GetRepositories
import br.com.charleston.domain.model.GithubModel
import br.com.charleston.github.features.search.states.SearchState
import javax.inject.Inject

interface InputVoiceSearchViewModel {
    fun listen()
    fun search(text: String)
}

interface OutputVoiceSearchViewModel {
    val search: LiveData<SearchState>
}

interface ContractVoiceSearchViewModel {
    val input: InputVoiceSearchViewModel
    val output: OutputVoiceSearchViewModel
}

class VoiceSearchViewModel @Inject constructor(
    private val getRepositories: GetRepositories
) : BaseViewModel(),
    ContractVoiceSearchViewModel,
    InputVoiceSearchViewModel,
    OutputVoiceSearchViewModel {

    override val input: InputVoiceSearchViewModel get() = this
    override val output: OutputVoiceSearchViewModel get() = this

    private val searchObservable = MutableLiveData<SearchState>()
    override val search: LiveData<SearchState> get() = searchObservable

    override fun listen() {
        searchObservable.postValue(SearchState.Listening)
    }

    override fun search(text: String) {
        getRepositories.execute(object : DefaultObserver<List<GithubModel>>() {
            override fun onStart() {
                searchObservable.postValue(SearchState.Loading(text))
            }

            override fun onNext(t: List<GithubModel>) {
                handlerSuccess(t)
            }

            override fun onError(exception: Throwable) {
                super.onError(exception)
                searchObservable.postValue(SearchState.Error(exception))
            }
        }, text)
    }

    private fun handlerSuccess(items: List<GithubModel>) {
        if (items.isNotEmpty()) {
            searchObservable.postValue(SearchState.Success(items))
        } else {
            searchObservable.postValue(SearchState.NoResult)
        }
    }
}