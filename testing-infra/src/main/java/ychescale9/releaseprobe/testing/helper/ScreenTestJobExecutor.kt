package ychescale9.releaseprobe.testing.helper

import android.os.AsyncTask
import java.util.concurrent.ThreadPoolExecutor
import ychescale9.infra.executor.ThreadExecutor

/**
 * Implementation of [ThreadExecutor] for running android tests.
 */
class ScreenTestJobExecutor : ThreadExecutor {

    /**
     * Use the threadPoolExecutor provided by AsyncTask so espresso knows how to synchronize
     */
    private val threadPoolExecutor: ThreadPoolExecutor = AsyncTask.THREAD_POOL_EXECUTOR as ThreadPoolExecutor

    override fun execute(command: Runnable) {
        this.threadPoolExecutor.execute(command)
    }
}
