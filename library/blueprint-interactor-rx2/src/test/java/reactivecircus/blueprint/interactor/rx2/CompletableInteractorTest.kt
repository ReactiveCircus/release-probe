package reactivecircus.blueprint.interactor.rx2

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.TestScheduler
import org.amshove.kluent.shouldBe
import org.junit.Test
import reactivecircus.blueprint.interactor.EmptyParams
import reactivecircus.blueprint.interactor.InteractorParams

class CompletableInteractorTest {

    @Suppress("ForbiddenVoid")
    lateinit var testObserver: TestObserver<Void>

    private val ioScheduler = TestScheduler()
    private val uiScheduler = TestScheduler()

    private val interactor = CompletableInteractorImpl(
        ioScheduler = ioScheduler,
        uiScheduler = uiScheduler
    )
    private val emptyParamsInteractor = EmptyParamsCompletableInteractorImpl(
        ioScheduler = ioScheduler,
        uiScheduler = uiScheduler
    )

    @Test
    fun `should complete when subscribed with params`() {
        testObserver = interactor.buildCompletable(CompletableParams(true)).test()

        ioScheduler.triggerActions()
        uiScheduler.triggerActions()

        testObserver.awaitTerminalEvent()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    fun `should complete when subscribed with empty params`() {
        testObserver = emptyParamsInteractor.buildCompletable(EmptyParams).test()

        ioScheduler.triggerActions()
        uiScheduler.triggerActions()

        testObserver.awaitTerminalEvent()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    fun `should complete synchronously when subscribed with params`() {
        testObserver = interactor.buildCompletable(
            CompletableParams(true), blocking = true
        ).test()

        testObserver.awaitTerminalEvent()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    fun `should complete synchronously when subscribed with empty params`() {
        testObserver = emptyParamsInteractor.buildCompletable(EmptyParams, blocking = true).test()

        testObserver.awaitTerminalEvent()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    fun `should set params`() {
        testObserver = interactor.buildCompletable(CompletableParams(true)).test()
        interactor.params.flag shouldBe true
    }

    private inner class CompletableInteractorImpl(
        ioScheduler: Scheduler,
        uiScheduler: Scheduler
    ) :
        CompletableInteractor<CompletableParams>(
            ioScheduler, uiScheduler
        ) {
        override fun createInteractor(): Completable {
            return Completable.complete()
        }
    }

    private inner class CompletableParams(val flag: Boolean) : InteractorParams

    private inner class EmptyParamsCompletableInteractorImpl(
        ioScheduler: Scheduler,
        uiScheduler: Scheduler
    ) : CompletableInteractor<EmptyParams>(
        ioScheduler, uiScheduler
    ) {
        override fun createInteractor(): Completable {
            return Completable.complete()
        }
    }
}
