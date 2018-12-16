package ychescale9.releaseprobe

import androidx.test.filters.LargeTest
import org.junit.Test
import ychescale9.releaseprobe.main.MainActivity
import ychescale9.releaseprobe.robot.mainScreen
import ychescale9.releaseprobe.testing.BaseScreenTest


@LargeTest
class MainScreenTest : BaseScreenTest() {

    @Test
    fun openMainScreenWithEmptyFeedsAndWatchlist_watchlistDestinationSelected() {
        mainScreen {
            given {
                // TODO given empty feeds and empty watchlist
            }
            perform {
                launchActivityScenario<MainActivity>()
            }
            check {
                // TODO watchlistDestinationSelected()
            }
        }
    }

    @Test
    fun openMainScreenWithExistingFeedsAndWatchlist_feedsDestinationSelected() {
        mainScreen {
            given {
                // TODO given some feeds and watchlist
            }
            perform {
                launchActivityScenario<MainActivity>()
            }
            check {
                feedsDestinationSelected()
            }
        }
    }

    @Test
    fun selectNewBottomNavigationItem_newDestinationSelected() {
        mainScreen {
            given {
                // TODO given some feeds and watchlist
            }
            perform {
                launchActivityScenario<MainActivity>()
                selectWatchlistNavItem()
            }
            check {
                watchlistDestinationSelected()
            }
            perform {
                selectBrowseCollectionsNavItem()
            }
            check {
                browseCollectionsDestinationSelected()
            }
            perform {
                selectSettingsNavItem()
            }
            check {
                settingsDestinationSelected()
            }
            perform {
                selectFeedsNavItem()
            }
            check {
                feedsDestinationSelected()
            }
        }
    }
}
