package reactivecircus.releaseprobe.feeds

import androidx.test.filters.LargeTest
import org.junit.Test
import reactivecircus.releaseprobe.testing.BaseScreenTest

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
