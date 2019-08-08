package reactivecircus.releaseprobe

import android.app.Activity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import reactivecircus.releaseprobe.artifactlist.ARG_COLLECTION_KEY
import reactivecircus.releaseprobe.core.AppNavigator

/**
 * Implementation of [AppNavigator].
 */
class ReleaseProbeAppNavigator : AppNavigator {

    override fun navigateToArtifactListScreen(
        activity: Activity,
        collectionName: String
    ) {
        activity.findNavController(R.id.mainNavHostFragment).navigate(
            R.id.action_global_artifactListFragment,
            bundleOf(ARG_COLLECTION_KEY to collectionName)
        )
    }
}
