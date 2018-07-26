package ychescale9.releaseprobe.watchlist

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import ychescale9.releaseprobe.testing.BaseScreenTest
import ychescale9.releaseprobe.testing.SingleFragmentActivity

@LargeTest
class WatchlistScreenTest : BaseScreenTest() {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, false)

    @Test
    fun openWatchlistScreenWithExistingItems_watchlistDisplayed() {
        launchActivityWithFragment(activityRule, WatchlistFragment())
        // TODO
    }

    @Test
    fun openWatchlistScreenWithoutExistingItems_emptyStateDisplayed() {
        launchActivityWithFragment(activityRule, WatchlistFragment())
        // TODO
    }
}
