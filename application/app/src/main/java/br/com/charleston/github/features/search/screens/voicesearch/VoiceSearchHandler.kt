package br.com.charleston.github.features.search.screens.voicesearch

import android.view.View

interface VoiceSearchHandler {
    fun onSearchStart()
    fun onCancelResult()
    fun onShowResult(view: View)
}