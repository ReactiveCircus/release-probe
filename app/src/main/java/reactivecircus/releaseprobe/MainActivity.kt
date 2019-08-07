package reactivecircus.releaseprobe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_ReleaseProbe_DayNight)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val topLevelDestinations = listOf(
            R.id.feedsFragment,
            R.id.watchlistFragment,
            R.id.browseCollectionsFragment,
            R.id.settingsFragment
        )

        val navController = findNavController(R.id.navHostFragment)
        val appBarConfig = AppBarConfiguration.Builder(
            topLevelDestinations.toSet()
        ).build()
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        setupActionBarWithNavController(this, navController, appBarConfig)

        // hide TopAppBar and BottomNavigationView when navigating to a destination
        // that isn't one of the top-level destinations
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when {
                destination.id in topLevelDestinations -> {
                    bottomNavigationView.isVisible = true
                    toolbar.isVisible = true
                }
                else -> {
                    bottomNavigationView.isVisible = false
                    toolbar.isVisible = false
                }
            }
        }

        // TODO SDK bug - fragments should NOT be recreated when re-selecting item in bottom navigation view
        //  https://issuetracker.google.com/issues/80029773
    }
}
