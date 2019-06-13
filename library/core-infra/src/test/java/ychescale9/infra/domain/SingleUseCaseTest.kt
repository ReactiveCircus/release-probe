package ychescale9.infra.domain

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.amshove.kluent.shouldBe
import org.junit.Test
import ychescale9.infra.SchedulerProvider

class SingleUseCaseTest {

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

    private val useCase = SingleUseCaseImpl(schedulerProvider)
    private val emptyParamsUseCase = EmptyParamsSingleUseCaseImpl(schedulerProvider)

    @Test
    fun `should receive value when subscribed with params`() {
        testObserver = useCase.buildSingle(SingleParams(true)).test()

        ioScheduler.triggerActions()
        uiScheduler.triggerActions()

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should receive value when subscribed with empty params`() {
        testObserver = emptyParamsUseCase.buildSingle(EmptyParams).test()

        ioScheduler.triggerActions()
        uiScheduler.triggerActions()

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should receive value synchronously when subscribed with params`() {
        testObserver = useCase.buildSingle(SingleParams(true), blocking = true).test()

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should receive value synchronously when subscribed with empty params`() {
        testObserver = emptyParamsUseCase.buildSingle(EmptyParams, blocking = true).test()

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should set params`() {
        testObserver = useCase.buildSingle(SingleParams(true)).test()
        useCase.params.flag shouldBe true
    }

    private inner class SingleUseCaseImpl(schedulerProvider: SchedulerProvider) :
        SingleUseCase<SingleParams, String>(schedulerProvider) {

        override fun createUseCase(): Single<String> {
            // return different results based on params
            return if (params.flag) {
                Single.just(dummyResult1)
            } else {
                Single.just(dummyResult2)
            }
        }
    }

    private inner class SingleParams(val flag: Boolean) : UseCaseParams

    private inner class EmptyParamsSingleUseCaseImpl(
        schedulerProvider: SchedulerProvider
    ) : SingleUseCase<EmptyParams, String>(schedulerProvider) {

        override fun createUseCase(): Single<String> {
            return Single.just(dummyResult1)
        }
    }
}
