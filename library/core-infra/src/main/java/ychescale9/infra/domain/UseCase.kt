package ychescale9.infra.domain

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import ychescale9.infra.executor.PostExecutionThread
import ychescale9.infra.executor.ThreadExecutor

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * Upon subscription a use case will execute its job in a background thread and will post the result in the UI thread.
 */
abstract class UseCase<Q : UseCase.RequestValues, T>(
        private val threadExecutor: ThreadExecutor,
        private val postExecutionThread: PostExecutionThread
) {
    lateinit var requestValues: Q

    protected abstract fun createUseCase(): Observable<T>

    /**
     * Return the created use case observable with the provided execution thread and post execution thread
     * @param requestValues
     * @return
     */
    fun get(requestValues: Q): Observable<T> {
        // update request values for the next execution
        this.requestValues = requestValues
        return createUseCase()
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler)
    }

    /**
     * Return the created use case observable without any changes to threading (blocking)
     *
     * NOTE: use this cautiously and only when you are sure that
     * it's fast and safe to run the use case on the current thread.
     * @param requestValues
     * @return
     */
    fun getBlocking(requestValues: Q): Observable<T> {
        // update request values for the next execution
        this.requestValues = requestValues
        return createUseCase()
    }

    /**
     * Request values passed in for a use case.
     */
    interface RequestValues
}

class EmptyRequestValues : UseCase.RequestValues
