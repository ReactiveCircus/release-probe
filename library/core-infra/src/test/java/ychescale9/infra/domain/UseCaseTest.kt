package ychescale9.infra.domain

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.amshove.kluent.shouldBe
import org.junit.Test
import ychescale9.infra.executor.PostExecutionThread
import ychescale9.infra.executor.ThreadExecutor

const val dummyResult1 = "result1"
const val dummyResult2 = "result2"

class UseCaseTest {

    lateinit var testObserver: TestObserver<String>

    private val useCase = UseCaseImpl(TestThreadExecutor(), TestPostExecutionThread())
    private val noRequestValuesUseCase = NoRequestValuesUseCaseImpl(TestThreadExecutor(), TestPostExecutionThread())

    @Test
    fun `should receive value when subscribed with request values`() {
        testObserver = useCase.get(UseCaseImpl.RequestValues(true)).test()
        testObserver.awaitTerminalEvent()

        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should receive value when subscribed with empty request values`() {
        testObserver = noRequestValuesUseCase.get(EmptyRequestValues()).test()
        testObserver.awaitTerminalEvent()

        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should receive value synchronously when subscribed with request values`() {
        testObserver = useCase.getBlocking(UseCaseImpl.RequestValues(true)).test()
        testObserver.awaitTerminalEvent()

        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should receive value synchronously when subscribed with empty request values`() {
        testObserver = noRequestValuesUseCase.getBlocking(EmptyRequestValues()).test()
        testObserver.awaitTerminalEvent()

        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should set request values`() {
        testObserver = useCase.get(UseCaseImpl.RequestValues(true)).test()
        useCase.requestValues.flag shouldBe true
    }
}

private class UseCaseImpl(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCase<UseCaseImpl.RequestValues, String>(threadExecutor, postExecutionThread) {

    override fun createUseCase(): Observable<String> {
        // return different results based on request values
        return if (requestValues.flag) {
            Observable.just(dummyResult1)
        } else {
            Observable.just(dummyResult2)
        }
    }

    class RequestValues(val flag: Boolean) : UseCase.RequestValues
}

private class NoRequestValuesUseCaseImpl(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCase<EmptyRequestValues, String>(threadExecutor, postExecutionThread) {

    override fun createUseCase(): Observable<String> {
        return Observable.just(dummyResult1)
    }
}

private class TestThreadExecutor : ThreadExecutor {

    override fun execute(runnable: Runnable) {
        // run on the current thread
        runnable.run()
    }
}

private class TestPostExecutionThread : PostExecutionThread {

    override val scheduler: Scheduler
        get() = Schedulers.trampoline()
}
