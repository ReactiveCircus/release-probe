package reactivecircus.releaseprobe.watchlist

import androidx.test.filters.LargeTest
import org.junit.Test
import reactivecircus.releaseprobe.testing.BaseScreenTest

@LargeTest
class WatchlistScreenTest : BaseScreenTest() {

    @Test
    fun openWatchlistScreenWithExistingItems_watchlistDisplayed() {
        launchFragmentScenario<WatchlistFragment>()
        // TODO
    }

    @Test
    fun openWatchlistScreenWithoutExistingItems_emptyStateDisplayed() {
        launchFragmentScenario<WatchlistFragment>()
        // TODO
    }
}
