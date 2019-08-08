package reactivecircus.releaseprobe.testing

import android.app.Activity
import reactivecircus.releaseprobe.core.AppNavigator

class NoOpAppNavigator : AppNavigator {

    override fun navigateToArtifactListScreen(
        activity: Activity,
        collectionName: String
    ) = Unit
}
