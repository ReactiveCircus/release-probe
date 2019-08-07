package reactivecircus.releaseprobe.robot

import android.content.Context
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import reactivecircus.blueprint.testing.BaseRobot
import reactivecircus.blueprint.testing.RobotActions
import reactivecircus.blueprint.testing.RobotAssertions
import reactivecircus.releaseprobe.R

fun mainScreen(block: MainRobot.() -> Unit) = MainRobot().apply { block() }

class MainRobot : BaseRobot<MainRobot.MainRobotActions, MainRobot.MainRobotAssertions>(
    MainRobotActions(), MainRobotAssertions()
) {
    val navHostViewId = R.id.navHostFragment

    class MainRobotActions : RobotActions() {

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

    class MainRobotAssertions : RobotAssertions() {

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
