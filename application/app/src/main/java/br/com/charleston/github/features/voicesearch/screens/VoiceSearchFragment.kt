package br.com.charleston.github.features.voicesearch.screens

import androidx.lifecycle.ViewModelProviders
import br.com.charleston.core.base.BaseFragment
import br.com.charleston.github.R
import br.com.charleston.github.databinding.FragmentVoiceSearchBinding
import br.com.charleston.github.features.voicesearch.viewmodel.VoiceSearchViewModel

class VoiceSearchFragment : BaseFragment<FragmentVoiceSearchBinding, VoiceSearchViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_voice_search
    }

    override fun getViewModel(): VoiceSearchViewModel {
        return ViewModelProviders
            .of(this, viewModelFactory)
            .get(VoiceSearchViewModel::class.java)
    }
}