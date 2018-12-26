package br.com.charleston.core.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable by lazy { CompositeDisposable() }

    internal fun onDestroyView() {
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }
}