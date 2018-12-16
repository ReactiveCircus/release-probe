@file:Suppress("MagicNumber")

package ychescale9.releaseprobe.testing.assumption

import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.mock.NetworkBehavior

fun assumeNetworkConnected() {
    networkAssumptions.networkBehavior.setFailurePercent(0)
}

fun assumeNetworkDisconnected() {
    networkAssumptions.networkBehavior.setFailurePercent(100)
}

private val networkAssumptions = NetworkAssumptions()

private class NetworkAssumptions : KoinComponent {
    internal val networkBehavior: NetworkBehavior by inject()
}
