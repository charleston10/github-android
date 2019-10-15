package br.com.charleston.github

import br.com.charleston.github.di.AppComponent
import br.com.charleston.github.di.DaggerAppComponent
import br.com.charleston.github.mocks.MockUrlApiModule

class TestAndroidApplication : AndroidApplication() {

    override fun createComponent(): AppComponent {
        return DaggerAppComponent
            .builder()
            .application(this)
            .moduleUrlApi(MockUrlApiModule())
            .build()
    }
}