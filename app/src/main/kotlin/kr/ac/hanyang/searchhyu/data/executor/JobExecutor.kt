package kr.ac.hanyang.searchhyu.data.executor

import kr.ac.hanyang.searchhyu.domain.executor.ThreadExecutor
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Decorated [java.util.concurrent.ThreadPoolExecutor].
 */
@Singleton
class JobExecutor @Inject constructor() : ThreadExecutor {

    private val threadPoolExecutor: ThreadPoolExecutor =
            ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS, LinkedBlockingDeque(), JobFactory())

    override fun execute(command: Runnable) {
        threadPoolExecutor.execute(command)
    }
}

private class JobFactory : ThreadFactory {
    private var counter: Int = 0

    override fun newThread(r: Runnable): Thread {
        return Thread(r, "SearchHYU_" + counter++)
    }
}
