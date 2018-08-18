package ychescale9.releaseprobe.browse

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import ychescale9.releaseprobe.testing.BaseScreenTest
import ychescale9.releaseprobe.testing.SingleFragmentActivity

@LargeTest
class BrowseScreenTest : BaseScreenTest() {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, false)

    @Test
    fun openBrowseScreen_artifactCollectionsDisplayed() {
        launchActivityWithFragment(activityRule, BrowseFragment())
        // TODO
    }
}
