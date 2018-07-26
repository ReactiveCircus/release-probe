package ychescale9.releaseprobe.feeds

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import ychescale9.releaseprobe.testing.BaseScreenTest
import ychescale9.releaseprobe.testing.SingleFragmentActivity

@LargeTest
class FeedsScreenTest : BaseScreenTest() {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, false)

    @Test
    fun openFeedsScreenWithExistingFeeds_feedsDisplayed() {
        launchActivityWithFragment(activityRule, FeedsFragment())
        // TODO
    }

    @Test
    fun openFeedsScreenWithoutExistingFeeds_emptyStateDisplayed() {
        launchActivityWithFragment(activityRule, FeedsFragment())
        // TODO
    }
}
