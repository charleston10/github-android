package br.com.charleston.github.di.modules

import androidx.lifecycle.ViewModel
import br.com.charleston.core.viewmodel.ViewModelKey
import br.com.charleston.github.features.voicesearch.viewmodel.VoiceSearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(VoiceSearchViewModel::class)
    abstract fun githubViewModel(viewModel: VoiceSearchViewModel): ViewModel
}