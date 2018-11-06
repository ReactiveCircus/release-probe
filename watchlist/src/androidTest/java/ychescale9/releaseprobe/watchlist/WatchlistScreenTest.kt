package ychescale9.releaseprobe.watchlist

import androidx.test.filters.LargeTest
import org.junit.Test
import ychescale9.releaseprobe.testing.BaseScreenTest

@LargeTest
class WatchlistScreenTest : BaseScreenTest() {

    @Test
    fun openWatchlistScreenWithExistingItems_watchlistDisplayed() {
        launchFragment<WatchlistFragment>()
        // TODO
    }

    @Test
    fun openWatchlistScreenWithoutExistingItems_emptyStateDisplayed() {
        launchFragment<WatchlistFragment>()
        // TODO
    }
}
