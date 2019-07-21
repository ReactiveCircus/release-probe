package reactivecircus.blueprint.threading.rx

import io.reactivex.Scheduler

class SchedulerProvider(
    val io: Scheduler,
    val computation: Scheduler,
    val ui: Scheduler
)
