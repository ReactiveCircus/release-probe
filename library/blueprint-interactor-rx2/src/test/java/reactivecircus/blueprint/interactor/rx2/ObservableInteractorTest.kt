package reactivecircus.blueprint.interactor.rx2

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.TestScheduler
import org.amshove.kluent.shouldBe
import org.junit.Test
import reactivecircus.blueprint.interactor.EmptyParams
import reactivecircus.blueprint.interactor.InteractorParams

class ObservableInteractorTest {

    private val dummyResult1 = "result1"
    private val dummyResult2 = "result2"

    lateinit var testObserver: TestObserver<String>

    private val ioScheduler = TestScheduler()
    private val uiScheduler = TestScheduler()

    private val interactor = ObservableInteractorImpl(
        ioScheduler = ioScheduler,
        uiScheduler = uiScheduler
    )
    private val emptyParamsInteractor = EmptyParamsObservableInteractorImpl(
        ioScheduler = ioScheduler,
        uiScheduler = uiScheduler
    )

    @Test
    fun `should receive value when subscribed with params`() {
        testObserver = interactor.buildObservable(ObservableParams(true)).test()

        ioScheduler.triggerActions()
        uiScheduler.triggerActions()

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should receive value when subscribed with empty params`() {
        testObserver = emptyParamsInteractor.buildObservable(EmptyParams).test()

        ioScheduler.triggerActions()
        uiScheduler.triggerActions()

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should receive value synchronously when subscribed with params`() {
        testObserver = interactor.buildObservable(ObservableParams(true), blocking = true).test()

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should receive value synchronously when subscribed with empty params`() {
        testObserver = emptyParamsInteractor.buildObservable(EmptyParams, blocking = true).test()

        testObserver.awaitTerminalEvent()
        testObserver.assertValue(dummyResult1)
        testObserver.assertNoErrors()
    }

    @Test
    fun `should set params`() {
        testObserver = interactor.buildObservable(ObservableParams(true)).test()
        interactor.params.flag shouldBe true
    }

    private inner class ObservableInteractorImpl(
        ioScheduler: Scheduler,
        uiScheduler: Scheduler
    ) :
        ObservableInteractor<ObservableParams, String>(
            ioScheduler, uiScheduler
        ) {
        override fun createInteractor(): Observable<String> {
            // return different results based on params
            return if (params.flag) {
                Observable.just(dummyResult1)
            } else {
                Observable.just(dummyResult2)
            }
        }
    }

    private inner class ObservableParams(val flag: Boolean) : InteractorParams

    private inner class EmptyParamsObservableInteractorImpl(
        ioScheduler: Scheduler,
        uiScheduler: Scheduler
    ) : ObservableInteractor<EmptyParams, String>(
        ioScheduler, uiScheduler
    ) {
        override fun createInteractor(): Observable<String> {
            return Observable.just(dummyResult1)
        }
    }
}
