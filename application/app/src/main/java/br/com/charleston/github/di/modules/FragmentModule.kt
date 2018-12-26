package br.com.charleston.github.di.modules

import br.com.charleston.github.features.voicesearch.screens.VoiceSearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun voiceSearchFragment(): VoiceSearchFragment
}