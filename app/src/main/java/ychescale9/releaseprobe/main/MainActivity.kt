package ychescale9.releaseprobe.main

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ychescale9.releaseprobe.R

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.navHostFragment)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        setupActionBarWithNavController(this, navController)

        // TODO check bottom navigation view behavior https://issuetracker.google.com/issues/80029773
        // TODO SDK bug - fragments should NOT be recreated when re-selecting item in bottom navigation view
        navController.addOnNavigatedListener { _, _ ->
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }

        // TODO navigate to watchlist screen if feeds and watchlist are both empty
    }
}
