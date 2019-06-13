package ychescale9.infra.domain

import io.reactivex.Single
import ychescale9.infra.SchedulerProvider

/**
 * Abstract class for a use case, representing an execution unit of asynchronous work.
 * This use case type uses [Single] as the return type.
 * Upon subscription a use case will execute its job in the io thread of the schedulerProvider
 * and will post the result in the UI thread.
 */
abstract class SingleUseCase<P : UseCaseParams, T>(private val schedulerProvider: SchedulerProvider) {

    lateinit var params: P

    protected abstract fun createUseCase(): Single<T>

    /**
     * Build a use case single with the provided execution thread and post execution thread
     * @param params
     * @param blocking - when set to true the single will be subscribed and observed on the current thread.
     * @return
     */
    fun buildSingle(params: P, blocking: Boolean = false): Single<T> {
        // update params for the next execution
        this.params = params
        return if (blocking) {
            createUseCase()
        } else {
            createUseCase()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
        }
    }
}
