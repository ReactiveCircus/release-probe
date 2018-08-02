package ychescale9.infra

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.never
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import ychescale9.infra.lifecycle.SingleLiveData

class SingleLiveDataTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    val owner = mock<LifecycleOwner>()

    lateinit var lifecycle: LifecycleRegistry

    val observer = mock<Observer<Int>>()

    val singleLiveData = SingleLiveData<Int>()

    @Before
    fun setUp() {
        // Link custom lifecycle owner with the lifecyle register.
        lifecycle = LifecycleRegistry(owner)
        whenever(owner.lifecycle).thenReturn(lifecycle)

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
        verify<Observer<Int>>(observer, never()).onChanged(anyInt())
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
        verify<Observer<Int>>(observer, times(1)).onChanged(anyInt())
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
        verify<Observer<Int>>(observer, times(2)).onChanged(anyInt())
    }

    @Test
    fun twoUpdates_noUpdateUntilActive() {
        // Set a value
        singleLiveData.value = 42

        // which doesn't emit a change
        verify<Observer<Int>>(observer, never()).onChanged(42)

        // and set it again
        singleLiveData.value = 42

        // observers are called once on resume.
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        // Check that the observer is called only once
        verify<Observer<Int>>(observer, times(1)).onChanged(anyInt())
    }
}
