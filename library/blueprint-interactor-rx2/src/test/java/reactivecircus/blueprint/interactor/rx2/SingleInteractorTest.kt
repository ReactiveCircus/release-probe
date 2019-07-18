package reactivecircus.blueprint.interactor.rx2

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.TestScheduler
import org.amshove.kluent.shouldBe
import org.junit.Test
import reactivecircus.blueprint.interactor.EmptyParams
import reactivecircus.blueprint.interactor.InteractorParams

class SingleInteractorTest {

    private val dummyResult1 = "result1"
    private val dummyResult2 = "result2"

    lateinit var testObserver: TestObserver<String>

    private val ioScheduler = TestScheduler()
    private val uiScheduler = TestScheduler()

    private val interactor = SingleInteractorImpl(
        ioScheduler = ioScheduler,
        uiScheduler = uiScheduler
    )
    private val emptyParamsInteractor = EmptyParamsSingleInteractorImpl(
        ioScheduler = ioScheduler,
        uiScheduler = uiScheduler
    )

    @Test
    fun `should receive value when subscribed with params`() {
        testObserver = interactor.buildSingle(SingleParams(true)).test()

        ioScheduler.triggerActions()
        uiScheduler.triggerActions()

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should receive value when subscribed with empty params`() {
        testObserver = emptyParamsInteractor.buildSingle(EmptyParams).test()

        ioScheduler.triggerActions()
        uiScheduler.triggerActions()

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should receive value synchronously when subscribed with params`() {
        testObserver = interactor.buildSingle(SingleParams(true), blocking = true).test()

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should receive value synchronously when subscribed with empty params`() {
        testObserver = emptyParamsInteractor.buildSingle(EmptyParams, blocking = true).test()

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should set params`() {
        testObserver = interactor.buildSingle(SingleParams(true)).test()
        interactor.params.flag shouldBe true
    }

    private inner class SingleInteractorImpl(
        ioScheduler: Scheduler,
        uiScheduler: Scheduler
    ) :
        SingleInteractor<SingleParams, String>(
            ioScheduler, uiScheduler
        ) {
        override fun createInteractor(): Single<String> {
            // return different results based on params
            return if (params.flag) {
                Single.just(dummyResult1)
            } else {
                Single.just(dummyResult2)
            }
        }
    }

    private inner class SingleParams(val flag: Boolean) : InteractorParams

    private inner class EmptyParamsSingleInteractorImpl(
        ioScheduler: Scheduler,
        uiScheduler: Scheduler
    ) : SingleInteractor<EmptyParams, String>(
        ioScheduler, uiScheduler
    ) {
        override fun createInteractor(): Single<String> {
            return Single.just(dummyResult1)
        }
    }
}
