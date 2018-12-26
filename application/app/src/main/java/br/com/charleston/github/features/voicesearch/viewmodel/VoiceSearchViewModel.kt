package br.com.charleston.github.features.voicesearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.charleston.core.base.BaseViewModel
import br.com.charleston.github.features.voicesearch.states.SearchState
import com.tbruyelle.rxpermissions2.RxPermissions
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
        searchObservable.postValue(SearchState.Loading(text))
    }
}