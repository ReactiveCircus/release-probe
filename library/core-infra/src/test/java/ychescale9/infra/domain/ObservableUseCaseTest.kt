package ychescale9.infra.domain

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.amshove.kluent.shouldBe
import org.junit.Test
import ychescale9.infra.SchedulerProvider

class ObservableUseCaseTest {

    private val dummyResult1 = "result1"
    private val dummyResult2 = "result2"

    lateinit var testObserver: TestObserver<String>

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

    private val useCase = ObservableUseCaseImpl(schedulerProvider)
    private val emptyParamsUseCase = EmptyParamsObservableUseCaseImpl(schedulerProvider)

    @Test
    fun `should receive value when subscribed with params`() {
        testObserver = useCase.buildObservable(ObservableParams(true)).test()

        ioScheduler.triggerActions()
        uiScheduler.triggerActions()

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should receive value when subscribed with empty params`() {
        testObserver = emptyParamsUseCase.buildObservable(EmptyParams).test()

        ioScheduler.triggerActions()
        uiScheduler.triggerActions()

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should receive value synchronously when subscribed with params`() {
        testObserver = useCase.buildObservable(ObservableParams(true), blocking = true).test()

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should receive value synchronously when subscribed with empty params`() {
        testObserver = emptyParamsUseCase.buildObservable(EmptyParams, blocking = true).test()

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should set params`() {
        testObserver = useCase.buildObservable(ObservableParams(true)).test()
        useCase.params.flag shouldBe true
    }

    private inner class ObservableUseCaseImpl(schedulerProvider: SchedulerProvider) :
        ObservableUseCase<ObservableParams, String>(schedulerProvider) {

        override fun createUseCase(): Observable<String> {
            // return different results based on params
            return if (params.flag) {
                Observable.just(dummyResult1)
            } else {
                Observable.just(dummyResult2)
            }
        }
    }

    private inner class ObservableParams(val flag: Boolean) : UseCaseParams

    private inner class EmptyParamsObservableUseCaseImpl(
        schedulerProvider: SchedulerProvider
    ) : ObservableUseCase<EmptyParams, String>(schedulerProvider) {

        override fun createUseCase(): Observable<String> {
            return Observable.just(dummyResult1)
        }
    }
}
