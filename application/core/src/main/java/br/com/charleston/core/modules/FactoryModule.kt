package br.com.charleston.core.modules

import androidx.lifecycle.ViewModelProvider
import br.com.charleston.core.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class FactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}