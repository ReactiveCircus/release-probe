package ychescale9.releaseprobe.feeds

import androidx.test.filters.LargeTest
import org.junit.Test
import ychescale9.releaseprobe.testing.BaseScreenTest

@LargeTest
class FeedsScreenTest : BaseScreenTest() {

    @Test
    fun openFeedsScreenWithExistingFeeds_feedsDisplayed() {
        launchFragmentScenario<FeedsFragment>()
        // TODO
    }

    @Test
    fun openFeedsScreenWithoutExistingFeeds_emptyStateDisplayed() {
        launchFragmentScenario<FeedsFragment>()
        // TODO
    }
}
