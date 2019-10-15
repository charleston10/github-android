package br.com.charleston.github.di.modules

import br.com.charleston.github.features.search.screens.detail.DetailFragment
import br.com.charleston.github.features.search.screens.list.ListFragment
import br.com.charleston.github.features.search.screens.voicesearch.VoiceSearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun voiceSearchFragment(): VoiceSearchFragment

    @ContributesAndroidInjector
    abstract fun listFragment(): ListFragment

    @ContributesAndroidInjector
    abstract fun detailFragment() : DetailFragment
}