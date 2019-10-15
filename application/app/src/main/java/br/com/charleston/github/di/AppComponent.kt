package br.com.charleston.github.di

import android.app.Application
import br.com.charleston.core.modules.AndroidModule
import br.com.charleston.core.modules.FactoryModule
import br.com.charleston.data.cloud.InterceptorModule
import br.com.charleston.data.cloud.NetworkModule
import br.com.charleston.data.cloud.RequestModule
import br.com.charleston.data.cloud.UrlApiModule
import br.com.charleston.data.di.GithubModule
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
        ViewModelModule::class,
        InterceptorModule::class,
        NetworkModule::class,
        RequestModule::class,
        UrlApiModule::class,
        GithubModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun moduleUrlApi(module: UrlApiModule): Builder

        fun build(): AppComponent
    }

    fun inject(app: AndroidApplication)
}