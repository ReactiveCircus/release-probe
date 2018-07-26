package ychescale9.releaseprobe

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import ychescale9.releaseprobe.main.MainActivity
import ychescale9.releaseprobe.robot.mainScreen
import ychescale9.releaseprobe.testing.BaseScreenTest

@LargeTest
class MainScreenTest : BaseScreenTest() {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun openMainScreenWithEmptyFeedsAndWatchlist_watchlistDestinationSelected() {
        mainScreen {
            given {
                // TODO given empty feeds and empty watchlist
            }
            perform {
                activityRule.launchActivity(null)
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
                activityRule.launchActivity(null)
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
                activityRule.launchActivity(null)
                selectWatchlistNavItem()
            }
            check {
                watchlistDestinationSelected()
            }
            perform {
                selectBrowseNavItem()
            }
            check {
                browseDestinationSelected()
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
