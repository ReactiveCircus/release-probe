package ychescale9.infra.domain

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.amshove.kluent.shouldBe
import org.junit.Test
import ychescale9.infra.SchedulerProvider

class CompletableUseCaseTest {

    @Suppress("ForbiddenVoid")
    lateinit var testObserver: TestObserver<Void>

    private val ioScheduler = TestScheduler()
    private val uiScheduler = TestScheduler()

    private val schedulerProvider = object : SchedulerProvider {
        override fun io(): Scheduler {
            return ioScheduler
        }

        override fun ui(): Scheduler {
            return uiScheduler
        }

        override fun computation(): Scheduler {
            return Schedulers.trampoline()
        }
    }

    private val useCase = CompletableUseCaseImpl(schedulerProvider)
    private val emptyParamsUseCase = EmptyParamsCompletableUseCaseImpl(schedulerProvider)

    @Test
    fun `should complete when subscribed with params`() {
        testObserver = useCase.buildCompletable(CompletableParams(true)).test()

        ioScheduler.triggerActions()
        uiScheduler.triggerActions()

        testObserver.awaitTerminalEvent()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    fun `should complete when subscribed with empty params`() {
        testObserver = emptyParamsUseCase.buildCompletable(EmptyParams).test()

        ioScheduler.triggerActions()
        uiScheduler.triggerActions()

        testObserver.awaitTerminalEvent()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    fun `should complete synchronously when subscribed with params`() {
        testObserver = useCase.buildCompletable(CompletableParams(true), blocking = true).test()

        testObserver.awaitTerminalEvent()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    fun `should complete synchronously when subscribed with empty params`() {
        testObserver = emptyParamsUseCase.buildCompletable(EmptyParams, blocking = true).test()

        testObserver.awaitTerminalEvent()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    fun `should set params`() {
        testObserver = useCase.buildCompletable(CompletableParams(true)).test()
        useCase.params.flag shouldBe true
    }

    private inner class CompletableUseCaseImpl(schedulerProvider: SchedulerProvider) :
        CompletableUseCase<CompletableParams>(schedulerProvider) {

        override fun createUseCase(): Completable {
            return Completable.complete()
        }
    }

    private inner class CompletableParams(val flag: Boolean) : UseCaseParams

    private inner class EmptyParamsCompletableUseCaseImpl(
        schedulerProvider: SchedulerProvider
    ) : CompletableUseCase<EmptyParams>(schedulerProvider) {

        override fun createUseCase(): Completable {
            return Completable.complete()
        }
    }
}
