package reactivecircus.blueprint.interactor.rx2

import io.reactivex.Observable
import io.reactivex.Scheduler
import reactivecircus.blueprint.interactor.InteractorParams

/**
 * Abstract class for a use case, representing an execution unit of asynchronous work.
 * This use case type uses [Observable] as the return type.
 * Upon subscription a use case will execute its job in the thread specified by the [ioScheduler].
 * and will post the result to the thread specified by [uiScheduler].
 */
abstract class ObservableInteractor<P : InteractorParams, T>(
    private val ioScheduler: Scheduler,
    private val uiScheduler: Scheduler
) {

    lateinit var params: P

    protected abstract fun createInteractor(): Observable<T>

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
            createInteractor()
        } else {
            createInteractor()
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
        }
    }
}
