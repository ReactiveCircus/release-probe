package ychescale9.infra

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ychescale9.infra.lifecycle.SingleLiveData

class SingleLiveDataTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var lifecycle: LifecycleRegistry

    val owner = mockk<LifecycleOwner>()

    val observer = mockk<Observer<Int>>(relaxed = true)

    val singleLiveData = SingleLiveData<Int>()

    @Before
    fun setUp() {
        // Link custom lifecycle owner with the lifecyle register.
        lifecycle = LifecycleRegistry(owner)
        every { owner.lifecycle } returns lifecycle

        // Start observing
        singleLiveData.observe(owner, observer)

        // Start in a non-active state
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }

    @Test
    fun valueNotSet_onFirstOnResume() {
        // On resume
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        // no update should be emitted because no value has been set
        verify(exactly = 0) { observer.onChanged(any()) }
    }

    @Test
    fun singleUpdate_onSecondOnResume_updatesOnce() {
        // After a value is set
        singleLiveData.value = 42

        // observers are called once on resume
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        // on second resume, no update should be emitted.
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        // Check that the observer is called once
        verify(exactly = 1) { observer.onChanged(42) }
    }

    @Test
    fun twoUpdates_updatesTwice() {
        // After a value is set
        singleLiveData.value = 42

        // observers are called once on resume
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        // when the value is set again, observers are called again.
        singleLiveData.value = 23

        // Check that the observer has been called twice
        verify(exactly = 2) { observer.onChanged(any()) }
    }

    @Test
    fun twoUpdates_noUpdateUntilActive() {
        // Set a value
        singleLiveData.value = 42

        // which doesn't emit a change
        verify(exactly = 0) { observer.onChanged(any()) }

        // and set it again
        singleLiveData.value = 42

        // observers are called once on resume.
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        // Check that the observer is called only once
        verify(exactly = 1) { observer.onChanged(42) }
    }
}
