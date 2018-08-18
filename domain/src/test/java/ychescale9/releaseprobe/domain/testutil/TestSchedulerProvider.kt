package ychescale9.releaseprobe.domain.testutil

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import ychescale9.infra.SchedulerProvider

/**
 * SchedulerProvider for unit tests - make sure unit tests run on the same thread
 */
class TestSchedulerProvider : SchedulerProvider {

    override fun ui(): Scheduler = Schedulers.trampoline()

    override fun io(): Scheduler = Schedulers.trampoline()

    override fun computation(): Scheduler = Schedulers.trampoline()
}