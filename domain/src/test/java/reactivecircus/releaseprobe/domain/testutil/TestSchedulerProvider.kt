package reactivecircus.releaseprobe.domain.testutil

import io.reactivex.schedulers.Schedulers
import reactivecircus.blueprint.threading.coroutines.SchedulerProvider

/**
 * SchedulerProvider for unit tests - make sure unit tests run on the same thread
 */
val testSchedulerProvider = SchedulerProvider(
    io = Schedulers.trampoline(),
    computation = Schedulers.trampoline(),
    ui = Schedulers.trampoline()
)
