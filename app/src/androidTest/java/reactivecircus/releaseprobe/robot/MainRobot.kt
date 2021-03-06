package reactivecircus.releaseprobe.robot

import android.content.Context
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import reactivecircus.blueprint.testing.RobotActions
import reactivecircus.blueprint.testing.RobotAssertions
import reactivecircus.blueprint.testing.ScreenRobot
import reactivecircus.blueprint.testing.action.clickRecyclerViewItem
import reactivecircus.blueprint.testing.action.selectBottomNavigationItem
import reactivecircus.blueprint.testing.assertion.bottomNavigationViewItemSelected
import reactivecircus.blueprint.testing.assertion.toolbarHasTitle
import reactivecircus.releaseprobe.R

fun mainScreen(block: MainRobot.() -> Unit) = MainRobot().apply { block() }

class MainRobot : ScreenRobot<MainRobot.Actions, MainRobot.Assertions>(Actions(), Assertions()) {
    val navHostViewId = R.id.mainNavHostFragment

    class Actions : RobotActions {

        fun selectFeedsNavItem() {
            val navItemTitle =
                getApplicationContext<Context>().resources.getString(R.string.menu_title_feeds)
            selectBottomNavigationItem(R.id.bottomNavigationView, navItemTitle)
        }

        fun selectWatchlistNavItem() {
            val navItemTitle =
                getApplicationContext<Context>().resources.getString(R.string.menu_title_watchlist)
            selectBottomNavigationItem(R.id.bottomNavigationView, navItemTitle)
        }

        fun selectBrowseCollectionsNavItem() {
            val navItemTitle =
                getApplicationContext<Context>().resources.getString(R.string.menu_title_browse)
            selectBottomNavigationItem(R.id.bottomNavigationView, navItemTitle)
        }

        fun selectSettingsNavItem() {
            val navItemTitle =
                getApplicationContext<Context>().resources.getString(R.string.menu_title_settings)
            selectBottomNavigationItem(R.id.bottomNavigationView, navItemTitle)
        }

        fun clickArtifactCollection(position: Int) {
            clickRecyclerViewItem(R.id.artifactCollectionsRecyclerView, position)
        }
    }

    class Assertions : RobotAssertions {

        fun feedsDestinationSelected() {
            toolbarHasTitle(R.string.title_feeds)
            bottomNavigationViewItemSelected(R.id.bottomNavigationView, R.id.feedsFragment)
        }

        fun watchlistDestinationSelected() {
            toolbarHasTitle(R.string.title_watchlist)
            bottomNavigationViewItemSelected(R.id.bottomNavigationView, R.id.watchlistFragment)
        }

        fun browseCollectionsDestinationSelected() {
            toolbarHasTitle(R.string.title_browse)
            bottomNavigationViewItemSelected(
                R.id.bottomNavigationView,
                R.id.browseCollectionsFragment
            )
        }

        fun settingsDestinationSelected() {
            toolbarHasTitle(R.string.title_settings)
            bottomNavigationViewItemSelected(R.id.bottomNavigationView, R.id.settingsFragment)
        }
    }
}
