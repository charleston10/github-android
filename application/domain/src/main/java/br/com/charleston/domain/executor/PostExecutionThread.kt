package br.com.charleston.domain.executor

import br.com.charleston.domain.executor.IPostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostExecutionThread @Inject constructor() : IPostExecutionThread {

    override fun getScheduler(): Scheduler {
        return Schedulers.io()
    }
}