package reactivecircus.blueprint.threading.coroutines

import io.reactivex.Scheduler

class SchedulerProvider(
    val io: Scheduler,
    val computation: Scheduler,
    val ui: Scheduler
)
