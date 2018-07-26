package ychescale9.infra.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class SchedulerProvider {

    open fun ui(): Scheduler = AndroidSchedulers.mainThread()

    open fun io(): Scheduler = Schedulers.io()

    open fun computation(): Scheduler = Schedulers.computation()
}
