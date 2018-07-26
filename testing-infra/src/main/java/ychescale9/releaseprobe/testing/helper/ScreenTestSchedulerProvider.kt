package ychescale9.releaseprobe.testing.helper

import android.os.AsyncTask
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import ychescale9.infra.rx.SchedulerProvider

/**
 * SchedulerProvider for screen tests
 */
class ScreenTestSchedulerProvider : SchedulerProvider() {

    override fun io(): Scheduler {
        // override default Schedulers.io() with AsyncTask.THREAD_POOL_EXECUTOR
        // as Espresso by default waits for async tasks to complete
        return Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR)
    }
}