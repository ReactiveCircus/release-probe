package reactivecircus.releaseprobe.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import reactivecircus.releaseprobe.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.navHostFragment)
        val appBarConfig = AppBarConfiguration.Builder(
            R.id.nav_feeds,
            R.id.nav_watchlist,
            R.id.nav_browse,
            R.id.nav_settings
        ).build()
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        setupActionBarWithNavController(this, navController, appBarConfig)

        // TODO SDK bug - fragments should NOT be recreated when re-selecting item in bottom navigation view - https://issuetracker.google.com/issues/80029773

        // TODO navigate to watchlist screen if feeds and watchlist are both empty
    }
}
