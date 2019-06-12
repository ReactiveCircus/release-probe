package ychescale9.releaseprobe.testing

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.FailureHandler
import androidx.test.espresso.base.DefaultFailureHandler
import androidx.test.espresso.intent.Intents
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.screenshot.Screenshot
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Assume
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestName
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import ychescale9.releaseprobe.data.artifactcollection.DefaultArtifactCollections
import ychescale9.releaseprobe.resources.R as ResourcesR
import ychescale9.releaseprobe.testing.assumption.assumeNetworkDisconnected
import ychescale9.uitest.annotation.PhoneTest
import ychescale9.uitest.annotation.TabletTest

abstract class BaseScreenTest : KoinComponent {

    val defaultArtifactCollections: DefaultArtifactCollections by inject()

    @Rule
    @JvmField
    val testName = TestName()

    @Before
    open fun setUp() {
        Intents.init()

        // skip if the test is not for the current device type
        assertDeviceOrSkip()

        // set up global failure handler
        Espresso.setFailureHandler(GlobalFailureHandler(ApplicationProvider.getApplicationContext<Context>()))
    }

    @After
    open fun tearDown() {
        Intents.release()
        // reset network connectivity
        assumeNetworkDisconnected()
    }

    inline fun <reified A : Activity> launchActivityScenario(
        intent: android.content.Intent? = null
    ): ActivityScenario<A> {
        return launchActivity<A>(intent).also {
            Espresso.onIdle()
        }
    }

    inline fun <reified F : Fragment> launchFragmentScenario(
        fragmentArgs: Bundle? = null,
        factory: FragmentFactory? = null
    ): FragmentScenario<F> {
        return launchFragmentInContainer<F>(
            fragmentArgs = fragmentArgs,
            themeResId = ResourcesR.style.Theme_RP,
            factory = factory
        ).also {
            getInstrumentation().waitForIdleSync()
        }
    }

    private class GlobalFailureHandler(targetContext: Context) : FailureHandler {

        private val delegate: FailureHandler

        init {
            delegate = DefaultFailureHandler(targetContext)
        }

        override fun handle(error: Throwable, viewMatcher: Matcher<View>) {
            Screenshot.capture()
            delegate.handle(error, viewMatcher)
        }
    }

    private fun assertDeviceOrSkip() {
        val m = javaClass.getMethod(testName.methodName)
        if (m.isAnnotationPresent(PhoneTest::class.java)) {
            Assume.assumeFalse(
                ApplicationProvider.getApplicationContext<Context>()
                    .resources.getBoolean(ResourcesR.bool.isTablet)
            )
        } else if (m.isAnnotationPresent(TabletTest::class.java)) {
            Assume.assumeTrue(
                ApplicationProvider.getApplicationContext<Context>()
                    .resources.getBoolean(ResourcesR.bool.isTablet)
            )
        }
    }
}
