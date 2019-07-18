package reactivecircus.blueprint.interactor.rx2

import io.reactivex.Completable
import io.reactivex.Scheduler
import reactivecircus.blueprint.interactor.InteractorParams

/**
 * Abstract class for a use case, representing an execution unit of asynchronous work.
 * This use case type uses [Completable] as the return type.
 * Upon subscription a use case will execute its job in the thread specified by the [ioScheduler].
 * and will post the result to the thread specified by [uiScheduler].
 */
abstract class CompletableInteractor<P : InteractorParams>(
    private val ioScheduler: Scheduler,
    private val uiScheduler: Scheduler
) {

    lateinit var params: P

    protected abstract fun createInteractor(): Completable

    /**
     * Build a use case completable with the provided execution thread and post execution thread
     * @param params
     * @param blocking - when set to true the completable will be subscribed and observed on the current thread.
     * @return
     */
    fun buildCompletable(params: P, blocking: Boolean = false): Completable {
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
