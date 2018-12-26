package br.com.charleston.domain.executor

import androidx.annotation.NonNull
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ThreadExecutor @Inject constructor() : IThreadExecutor {

    private val threadPoolExecutor: ThreadPoolExecutor by lazy {
        ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS,
                LinkedBlockingQueue(), JobThreadFactory()
        )
    }

    override fun execute(@NonNull runnable: Runnable) {
        this.threadPoolExecutor.execute(runnable)
    }

    private class JobThreadFactory : ThreadFactory {
        private var counter = 0

        override fun newThread(@NonNull runnable: Runnable): Thread {
            return Thread(runnable, "android_" + counter++)
        }
    }
}