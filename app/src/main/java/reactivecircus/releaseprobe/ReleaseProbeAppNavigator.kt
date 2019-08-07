package reactivecircus.releaseprobe

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import reactivecircus.releaseprobe.artifactlist.ARG_COLLECTION_KEY
import reactivecircus.releaseprobe.core.AppNavigator

/**
 * Implementation of [AppNavigator].
 */
class ReleaseProbeAppNavigator : AppNavigator {

    override fun navigateToArtifactListScreen(
        navController: NavController,
        collectionName: String
    ) {
        navController.navigate(
            R.id.action_global_artifactListFragment,
            bundleOf(ARG_COLLECTION_KEY to collectionName)
        )
    }
}
