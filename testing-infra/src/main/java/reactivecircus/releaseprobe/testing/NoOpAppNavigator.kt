package reactivecircus.releaseprobe.testing

import androidx.navigation.NavController
import reactivecircus.releaseprobe.core.AppNavigator

class NoOpAppNavigator : AppNavigator {

    override fun navigateToArtifactListScreen(
        navController: NavController,
        collectionName: String
    ) {
        // TODO
    }
}
