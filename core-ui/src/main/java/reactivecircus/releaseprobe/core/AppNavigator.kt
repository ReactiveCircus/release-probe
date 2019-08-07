package reactivecircus.releaseprobe.core

import androidx.navigation.NavController

/**
 * Performs cross-boundary screen navigation within the app using [NavController].
 */
interface AppNavigator {

    fun navigateToArtifactListScreen(navController: NavController, collectionName: String)
}
