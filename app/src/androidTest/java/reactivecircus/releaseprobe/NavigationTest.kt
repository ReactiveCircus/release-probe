package reactivecircus.releaseprobe

import androidx.test.filters.LargeTest
import org.junit.Test
import reactivecircus.blueprint.testing.assertion.fragmentDisplayed
import reactivecircus.releaseprobe.artifactlist.ArtifactListFragment
import reactivecircus.releaseprobe.robot.mainScreen
import reactivecircus.releaseprobe.testing.BaseScreenTest

@LargeTest
class NavigationTest : BaseScreenTest() {

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

    @Test
    fun clickArtifactCollection_browseArtifactsScreenLaunched() {
        mainScreen {
            given {
                // TODO given some feeds and watchlist
            }
            perform {
                launchActivityScenario<MainActivity>()
                selectBrowseCollectionsNavItem()
                clickArtifactCollection(0)
            }
            check {
                fragmentDisplayed<ArtifactListFragment>(navHostViewId)
            }
        }
    }
}
