package ychescale9.infra.domain

import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.amshove.kluent.shouldBe
import org.junit.Test
import ychescale9.infra.SchedulerProvider

const val dummyResult1 = "result1"
const val dummyResult2 = "result2"

class UseCaseTest {

    lateinit var testObserver: TestObserver<String>

    private val useCase = UseCaseImpl(TestSchedulerProvider())
    private val emptyParamsUseCase = EmptyParamsUseCaseImpl(TestSchedulerProvider())

    @Test
    fun `should receive value when subscribed with params`() {
        testObserver = useCase.get(UseCaseImpl.Params(true)).test()
        testObserver.awaitTerminalEvent()

        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should receive value when subscribed with empty params`() {
        testObserver = emptyParamsUseCase.get(EmptyParams()).test()
        testObserver.awaitTerminalEvent()

        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should receive value synchronously when subscribed with params`() {
        testObserver = useCase.getBlocking(UseCaseImpl.Params(true)).test()
        testObserver.awaitTerminalEvent()

        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should receive value synchronously when subscribed with empty params`() {
        testObserver = emptyParamsUseCase.getBlocking(EmptyParams()).test()
        testObserver.awaitTerminalEvent()

        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should set params`() {
        testObserver = useCase.get(UseCaseImpl.Params(true)).test()
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

private class TestSchedulerProvider : SchedulerProvider {

    override fun ui() = Schedulers.trampoline()

    override fun io() = Schedulers.trampoline()

    override fun computation() = Schedulers.trampoline()
}
