package br.com.charleston.domain

import io.reactivex.observers.DisposableObserver

open class DefaultObserver<T> : DisposableObserver<T>() {
    override fun onNext(t: T) {}
    override fun onComplete() {}
    override fun onError(exception: Throwable) {
        exception.printStackTrace()
    }
}