package br.com.charleston.github.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import br.com.charleston.github.TestAndroidApplication

class MockTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, TestAndroidApplication::class.java.name, context)
    }
}