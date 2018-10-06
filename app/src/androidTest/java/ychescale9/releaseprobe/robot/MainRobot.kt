package ychescale9.releaseprobe.robot

import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import ychescale9.releaseprobe.R
import ychescale9.uitest.BaseRobot
import ychescale9.uitest.RobotActions
import ychescale9.uitest.RobotAssertions

fun mainScreen(block: MainRobot.() -> Unit) = MainRobot().apply { block() }

class MainRobot : BaseRobot<MainRobotActions, MainRobotAssertions>(
        MainRobotActions(), MainRobotAssertions()
)

class MainRobotActions : RobotActions() {

    fun selectFeedsNavItem() {
        val navItemTitle = getInstrumentation().targetContext.resources.getString(R.string.menu_title_feeds)
        selectBottomNavigationItem(R.id.bottomNavigationView, navItemTitle)
    }

    fun selectWatchlistNavItem() {
        val navItemTitle = getInstrumentation().targetContext.resources.getString(R.string.menu_title_watchlist)
        selectBottomNavigationItem(R.id.bottomNavigationView, navItemTitle)
    }

    fun selectBrowseCollectionsNavItem() {
        val navItemTitle = getInstrumentation().targetContext.resources.getString(R.string.menu_title_browse)
        selectBottomNavigationItem(R.id.bottomNavigationView, navItemTitle)
    }

    fun selectSettingsNavItem() {
        val navItemTitle = getInstrumentation().targetContext.resources.getString(R.string.menu_title_settings)
        selectBottomNavigationItem(R.id.bottomNavigationView, navItemTitle)
    }
}

class MainRobotAssertions : RobotAssertions() {

    fun feedsDestinationSelected() {
        val titleString = getInstrumentation().targetContext.resources.getString(R.string.title_feeds)
        toolbarHasTitle(titleString)
        bottomNavigationViewItemSelected(R.id.bottomNavigationView, R.id.nav_feeds)
    }

    fun watchlistDestinationSelected() {
        val titleString = getInstrumentation().targetContext.resources.getString(R.string.title_watchlist)
        toolbarHasTitle(titleString)
        bottomNavigationViewItemSelected(R.id.bottomNavigationView, R.id.nav_watchlist)
    }

    fun browseCollectionsDestinationSelected() {
        val titleString = getInstrumentation().targetContext.resources.getString(R.string.title_browse)
        toolbarHasTitle(titleString)
        bottomNavigationViewItemSelected(R.id.bottomNavigationView, R.id.nav_browse)
    }

    fun settingsDestinationSelected() {
        val titleString = getInstrumentation().targetContext.resources.getString(R.string.title_settings)
        toolbarHasTitle(titleString)
        bottomNavigationViewItemSelected(R.id.bottomNavigationView, R.id.nav_settings)
    }
}
