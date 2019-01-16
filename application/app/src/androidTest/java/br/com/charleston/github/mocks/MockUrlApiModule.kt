package br.com.charleston.github.mocks

import br.com.charleston.data.cloud.UrlApiModule
import br.com.charleston.github.config.MOCK_SERVER

class MockUrlApiModule : UrlApiModule() {
    override fun provideUrl(): String {
        return MOCK_SERVER
    }
}