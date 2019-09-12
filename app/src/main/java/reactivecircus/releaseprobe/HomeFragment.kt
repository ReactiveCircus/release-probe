package reactivecircus.releaseprobe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import reactivecircus.releaseprobe.core.base.BaseFragment
import reactivecircus.releaseprobe.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(inflater, container, false).let {
        binding = it
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topLevelDestinations = listOf(
            R.id.feedsFragment,
            R.id.watchlistFragment,
            R.id.browseCollectionsFragment,
            R.id.settingsFragment
        )

        val fragmentContainer = view.findViewById<View>(R.id.homeNavHostFragment)
        val navController = Navigation.findNavController(fragmentContainer)
        val appBarConfig = AppBarConfiguration.Builder(
            topLevelDestinations.toSet()
        ).build()

        // setup NavController with BottomNavigationView
        NavigationUI.setupWithNavController(
            binding.bottomNavigationView,
            navController
        )

        // setup NavController with Toolbar
        NavigationUI.setupWithNavController(
            binding.toolbar,
            navController,
            appBarConfig
        )

        // TODO SDK bug - fragments should NOT be recreated when re-selecting item in bottom navigation view
        //  https://issuetracker.google.com/issues/80029773
    }
}
