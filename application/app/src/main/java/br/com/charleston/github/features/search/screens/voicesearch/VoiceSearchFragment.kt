package br.com.charleston.github.features.search.screens.voicesearch

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import br.com.charleston.core.base.BaseFragment
import br.com.charleston.domain.model.GithubModel
import br.com.charleston.github.R
import br.com.charleston.github.databinding.FragmentVoiceSearchBinding
import br.com.charleston.github.extensions.disable
import br.com.charleston.github.extensions.enable
import br.com.charleston.github.extensions.typeWriter
import br.com.charleston.github.features.search.states.SearchState
import br.com.charleston.github.features.search.viewmodel.VoiceSearchViewModel
import com.tbruyelle.rxpermissions2.RxPermissions


class VoiceSearchFragment
    : BaseFragment<FragmentVoiceSearchBinding, VoiceSearchViewModel>(),
        RecognitionListener,
        VoiceSearchHandler {

    private var items: List<GithubModel> = arrayListOf()

    private val speech by lazy {
        SpeechRecognizer.createSpeechRecognizer(context).apply {
            setRecognitionListener(this@VoiceSearchFragment)
        }
    }

    private val rxPermissions by lazy { RxPermissions(activity!!) }

    companion object {
        private const val MAX_RESULT = 1
        private const val LANGUAGE_PREFERENCE = "pt"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerViewModel()
        bindView()
    }

    override fun onStop() {
        super.onStop()
        cancelSpeech()
        getViewDataBinding().showResult = false
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_voice_search
    }

    override fun getViewModel(): VoiceSearchViewModel {
        return ViewModelProviders
                .of(this, viewModelFactory)
                .get(VoiceSearchViewModel::class.java)
    }

    override fun onReadyForSpeech(params: Bundle?) {}
    override fun onRmsChanged(rmsdB: Float) {}
    override fun onBufferReceived(buffer: ByteArray?) {}
    override fun onPartialResults(partialResults: Bundle?) {}
    override fun onEvent(eventType: Int, params: Bundle?) {}
    override fun onBeginningOfSpeech() {}

    override fun onEndOfSpeech() {
        stopSpeech()
    }

    override fun onError(error: Int) {
        val errorMessage = getErrorSpeech(error)
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        resetMessage()
    }

    override fun onResults(results: Bundle?) {
        val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        search(matches?.get(0)!!)
    }

    @SuppressLint("CheckResult")
    override fun onSearchStart() {
        rxPermissions
                .request(Manifest.permission.RECORD_AUDIO)
                .subscribe { granted ->
                    when {
                        granted -> getViewModel().input.listen()
                        else -> handlerPermissionError()
                    }
                }
    }

    override fun onCancelResult() {
        getViewDataBinding().showResult = false
        resetMessage()
    }

    override fun onShowResult(view: View) {
        startListResult()
    }

    private fun bindView() {
        getViewDataBinding().run {
            handlers = this@VoiceSearchFragment
            showResult = false
        }
    }

    private fun observerViewModel() {
        getViewModel().output.run {
            search.observe(this@VoiceSearchFragment,
                    Observer {
                        renderSearchState(it)
                    })
        }
    }

    private fun renderSearchState(state: SearchState) {
        when (state) {
            is SearchState.Listening -> {
                playSpeech()
            }
            is SearchState.Success -> {
                setList(state.data)
                enableSearch()
                getViewDataBinding().showResult = true
                bindMessageResult(state.searchingByText)
            }
            is SearchState.Loading -> {
                disableSearch()
                bindMessageSearching(state.searchingByText)
            }
            is SearchState.NoResult -> {
                showErrorNoResult()
                enableSearch()
            }
            is SearchState.Error -> {
                showError(state.error)
                enableSearch()
            }
        }
    }

    private fun bindMessageSearching(value: String?) {
        bindMessage(value, getString(R.string.voice_search_message_find_repository_by))
    }

    private fun bindMessageResult(value: String) {
        bindMessage(value, getString(R.string.voice_search_message_result_repository_by))
    }

    private fun bindMessage(value: String?, message: String) {
        getViewDataBinding().message.typeWriter(
                String.format(
                        message,
                        value!!.toUpperCase()
                )
        )
    }

    private fun search(text: String) {
        getViewModel().input.search(text)
    }

    private fun playSpeech() {
        getViewDataBinding().run {
            message.typeWriter(getString(R.string.voice_search_message_listening))
            voice.playAnimation()
        }
        speech.startListening(speechIntent())
    }

    private fun stopSpeech() {
        speech.stopListening()
        cancelAnimation()
    }

    private fun cancelSpeech() {
        if (speech != null) {
            speech.cancel()
            cancelAnimation()
            resetMessage()
        }
    }

    private fun setList(items: List<GithubModel>) {
        this.items = items
    }

    private fun startListResult() {
        val action = VoiceSearchFragmentDirections
                .ActionVoiceSearchFragmentToListFragment(items.toTypedArray())

        Navigation.findNavController(view!!).navigate(action)
    }

    private fun cancelAnimation() {
        getViewDataBinding().voice.run {
            frame = 0
            cancelAnimation()
        }
    }

    private fun speechIntent(): Intent {
        return Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,
                    LANGUAGE_PREFERENCE
            )
            putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, activity?.packageName)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH)
            putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, activity?.packageName)
            putExtra(
                    RecognizerIntent.EXTRA_MAX_RESULTS,
                    MAX_RESULT
            )
        }
    }

    private fun getErrorSpeech(errorCode: Int): String {
        return when (errorCode) {
            SpeechRecognizer.ERROR_AUDIO -> "Audio recording error"
            SpeechRecognizer.ERROR_CLIENT -> "Client side error"
            SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Insufficient permissions"
            SpeechRecognizer.ERROR_NETWORK -> "Network error"
            SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Network timeout"
            SpeechRecognizer.ERROR_NO_MATCH -> "No match"
            SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "RecognitionService busy"
            SpeechRecognizer.ERROR_SERVER -> "error from server"
            SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "No speech input"
            else -> "Didn't understand, please try again."
        }
    }

    private fun handlerPermissionError() {
        getViewDataBinding().run {
            message.typeWriter(getString(R.string.voice_search_message_permission_error), 10)
            voice.visibility = View.GONE
        }
    }

    private fun resetMessage() {
        getViewDataBinding().message.text = getString(R.string.voice_search_message_inital)
    }

    private fun showErrorNoResult() {
        getViewDataBinding().message.text = getString(R.string.voice_search_message_no_result)
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(context, throwable.message, Toast.LENGTH_LONG).show()
    }

    private fun disableSearch() {
        getViewDataBinding().voice.disable()
    }

    private fun enableSearch() {
        getViewDataBinding().voice.enable()
    }
}