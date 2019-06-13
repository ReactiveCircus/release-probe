package ychescale9.infra.domain

import io.reactivex.Observable
import ychescale9.infra.SchedulerProvider

/**
 * Abstract class for a use case, representing an execution unit of asynchronous work.
 * This use case type uses [Observable] as the return type.
 * Upon subscription a use case will execute its job in the io thread of the schedulerProvider
 * and will post the result in the UI thread.
 */
abstract class ObservableUseCase<P : UseCaseParams, T>(private val schedulerProvider: SchedulerProvider) {

    lateinit var params: P

    protected abstract fun createUseCase(): Observable<T>

    /**
     * Build a use case observable with the provided execution thread and post execution thread
     * @param params
     * @param blocking - when set to true the observable will be subscribed and observed on the current thread.
     * @return
     */
    fun buildObservable(params: P, blocking: Boolean = false): Observable<T> {
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
