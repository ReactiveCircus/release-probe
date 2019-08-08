package reactivecircus.releaseprobe.core

import android.app.Activity
import androidx.navigation.NavController

/**
 * Performs cross-boundary screen navigation within the app using [NavController].
 */
interface AppNavigator {

    fun navigateToArtifactListScreen(activity: Activity, collectionName: String)
}
