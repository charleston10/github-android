package br.com.charleston.core.base

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel>
    : AppCompatActivity(), HasSupportFragmentInjector {

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private val dataBinding: T by lazy { DataBindingUtil.setContentView<T>(this, getLayoutId()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        executeDataBinding()
        bindStatusBar()
    }

    override fun onDestroy() {
        super.onDestroy()
        getViewModel().onDestroyView()
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    fun getViewDataBinding(): T {
        return dataBinding
    }

    private fun executeDataBinding() {
        dataBinding.executePendingBindings()
    }

    private fun bindStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
}