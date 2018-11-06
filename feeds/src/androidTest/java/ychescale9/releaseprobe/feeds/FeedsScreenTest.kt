package ychescale9.releaseprobe.feeds

import androidx.test.filters.LargeTest
import org.junit.Test
import ychescale9.releaseprobe.testing.BaseScreenTest

@LargeTest
class FeedsScreenTest : BaseScreenTest() {

    @Test
    fun openFeedsScreenWithExistingFeeds_feedsDisplayed() {
        launchFragment<FeedsFragment>()
        // TODO
    }

    @Test
    fun openFeedsScreenWithoutExistingFeeds_emptyStateDisplayed() {
        launchFragment<FeedsFragment>()
        // TODO
    }
}
