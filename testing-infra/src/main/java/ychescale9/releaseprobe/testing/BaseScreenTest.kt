package ychescale9.releaseprobe.testing

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso
import androidx.test.espresso.FailureHandler
import androidx.test.espresso.base.DefaultFailureHandler
import androidx.test.espresso.intent.Intents
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.screenshot.Screenshot
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Assume
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestName
import retrofit2.mock.NetworkBehavior
import ychescale9.uitest.annotation.PhoneTest
import ychescale9.uitest.annotation.TabletTest
import javax.inject.Inject

abstract class BaseScreenTest {

    @Inject
    lateinit var networkBehavior: NetworkBehavior

    @Rule
    @JvmField
    val testName = TestName()

    @Before
    open fun setUp() {
        Intents.init()

        // skip if the test is not for the current device type
        assertDeviceOrSkip()

        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val testApp = instrumentation.targetContext.applicationContext as ScreenTestApp
        val component = testApp.testAppComponent()
        component.inject(this)

        // set up global failure handler
        Espresso.setFailureHandler(GlobalFailureHandler(InstrumentationRegistry.getInstrumentation().targetContext))
    }

    @After
    open fun tearDown() {
        Intents.release()
        // reset network connectivity
        givenNetworkIsConnected()
    }

    fun launchActivityWithFragment(activityTestRule: ActivityTestRule<SingleFragmentActivity>, fragment: Fragment) {
        activityTestRule.apply {
            launchActivity(null)
            activity.setFragment(fragment)
        }
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
    }

    fun givenNetworkIsConnected() {
        networkBehavior.setFailurePercent(0)
    }

    fun givenNetworkIsNotConnected() {
        networkBehavior.setFailurePercent(100)
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
            Assume.assumeFalse(InstrumentationRegistry.getInstrumentation().targetContext.resources.getBoolean(R.bool.isTablet))
        } else if (m.isAnnotationPresent(TabletTest::class.java)) {
            Assume.assumeTrue(InstrumentationRegistry.getInstrumentation().targetContext.resources.getBoolean(R.bool.isTablet))
        }
    }
}
