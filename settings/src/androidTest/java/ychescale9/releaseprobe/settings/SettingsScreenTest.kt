package ychescale9.releaseprobe.settings

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import ychescale9.releaseprobe.testing.BaseScreenTest
import ychescale9.releaseprobe.testing.SingleFragmentActivity

@LargeTest
class SettingsScreenTest : BaseScreenTest() {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, false)

    @Test
    fun openSettingsScreenWithDefaultSettings_defaultSettingsDisplayed() {
        launchActivityWithFragment(activityRule, SettingsFragment())
        // TODO
    }
}
