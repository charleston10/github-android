package br.com.charleston.github.features.voicesearch.screens

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
import br.com.charleston.core.base.BaseFragment
import br.com.charleston.github.R
import br.com.charleston.github.databinding.FragmentVoiceSearchBinding
import br.com.charleston.github.extensions.typeWriter
import br.com.charleston.github.features.voicesearch.states.SearchState
import br.com.charleston.github.features.voicesearch.viewmodel.VoiceSearchViewModel
import com.tbruyelle.rxpermissions2.RxPermissions


class VoiceSearchFragment
    : BaseFragment<FragmentVoiceSearchBinding, VoiceSearchViewModel>(),
    RecognitionListener,
    VoiceSearchHandler {

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

    override fun onPause() {
        super.onPause()
        cancelSpeech()
    }

    override fun onStop() {
        super.onStop()
        cancelSpeech()
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

    private fun bindView() {
        getViewDataBinding().handlers = this
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
            is SearchState.Listening -> playSpeech()
            is SearchState.Error -> handlerError(state.error)
            is SearchState.Success -> stopSpeech()
            is SearchState.Loading -> searching(state.searchingByText)
        }
    }

    private fun searching(text: String?) {
        val searchingText = text!!.toUpperCase()
        getViewDataBinding().message.typeWriter("Searching repository by\n$searchingText")
    }

    private fun search(text: String) {
        getViewModel().input.search(text)
    }

    private fun playSpeech() {
        getViewDataBinding().run {
            message.typeWriter("Listening...")
            voice.playAnimation()
        }
        speech.startListening(speechIntent())
    }

    private fun stopSpeech() {
        speech.stopListening()
        getViewDataBinding().voice.run {
            frame = 0
            cancelAnimation()
        }
    }

    private fun cancelSpeech() {
        if (speech != null) speech.cancel()
    }

    private fun speechIntent(): Intent {
        return Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, LANGUAGE_PREFERENCE)
            putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, activity?.packageName)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH)
            putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, activity?.packageName)
            putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, MAX_RESULT)
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

    private fun handlerError(throwable: Throwable) {
        stopSpeech()
        Toast.makeText(context, throwable.message, Toast.LENGTH_LONG).show()
    }

    private fun handlerPermissionError() {
        getViewDataBinding().run {
            message.typeWriter("Record audio was declined for search", 10)
            voice.visibility = View.GONE
        }
    }
}