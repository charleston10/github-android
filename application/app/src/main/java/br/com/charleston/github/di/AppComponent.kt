package br.com.charleston.github.di

import android.app.Application
import br.com.charleston.core.modules.AndroidModule
import br.com.charleston.core.modules.FactoryModule
import br.com.charleston.github.AndroidApplication
import br.com.charleston.github.di.modules.ActivityModule
import br.com.charleston.github.di.modules.FragmentModule
import br.com.charleston.github.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        FactoryModule::class,
        AndroidModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: AndroidApplication)
}