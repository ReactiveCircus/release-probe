package ychescale9.infra.domain

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.amshove.kluent.shouldBe
import org.junit.Test
import ychescale9.infra.SchedulerProvider

const val dummyResult1 = "result1"
const val dummyResult2 = "result2"

class UseCaseTest {

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

    private val useCase = UseCaseImpl(schedulerProvider)
    private val emptyParamsUseCase = EmptyParamsUseCaseImpl(schedulerProvider)

    @Test
    fun `should receive value when subscribed with params`() {
        testObserver = useCase.buildObservable(UseCaseImpl.Params(true)).test()

        ioScheduler.triggerActions()
        uiScheduler.triggerActions()

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should receive value when subscribed with empty params`() {
        testObserver = emptyParamsUseCase.buildObservable(EmptyParams()).test()

        ioScheduler.triggerActions()
        uiScheduler.triggerActions()

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should receive value synchronously when subscribed with params`() {
        testObserver = useCase.buildObservable(UseCaseImpl.Params(true), blocking = true).test()

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should receive value synchronously when subscribed with empty params`() {
        testObserver = emptyParamsUseCase.buildObservable(EmptyParams(), blocking = true).test()

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should set params`() {
        testObserver = useCase.buildObservable(UseCaseImpl.Params(true)).test()
        useCase.params.flag shouldBe true
    }
}

private class UseCaseImpl(schedulerProvider: SchedulerProvider) : UseCase<UseCaseImpl.Params, String>(schedulerProvider) {

    override fun createUseCase(): Observable<String> {
        // return different results based on params
        return if (params.flag) {
            Observable.just(dummyResult1)
        } else {
            Observable.just(dummyResult2)
        }
    }

    class Params(val flag: Boolean) : UseCase.Params
}

private class EmptyParamsUseCaseImpl(
    schedulerProvider: SchedulerProvider
) : UseCase<EmptyParams, String>(schedulerProvider) {

    override fun createUseCase(): Observable<String> {
        return Observable.just(dummyResult1)
    }
}
