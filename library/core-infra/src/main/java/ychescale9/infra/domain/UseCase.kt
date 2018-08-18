package ychescale9.infra.domain

import io.reactivex.Observable
import ychescale9.infra.SchedulerProvider

/**
 * Abstract class for a use case, representing an execution unit of asynchronous work.
 * Upon subscription a use case will execute its job in the io thread of the schedulerProvider
 * and will post the result in the UI thread.
 */
abstract class UseCase<P : UseCase.Params, T>(private val schedulerProvider: SchedulerProvider) {

    lateinit var params: P

    protected abstract fun createUseCase(): Observable<T>

    /**
     * Return the created use case observable with the provided execution thread and post execution thread
     * @param params
     * @return
     */
    fun get(params: P): Observable<T> {
        // update params for the next execution
        this.params = params
        return createUseCase()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
    }

    /**
     * Return the created use case observable without any changes to threading (blocking)
     *
     * NOTE: use this cautiously and only when you are sure that
     * it's fast and safe to run the use case on the current thread.
     * @param params
     * @return
     */
    fun getBlocking(params: P): Observable<T> {
        // update params for the next execution
        this.params = params
        return createUseCase()
    }

    /**
     * Params passed in for a use case.
     */
    interface Params
}

class EmptyParams : UseCase.Params
