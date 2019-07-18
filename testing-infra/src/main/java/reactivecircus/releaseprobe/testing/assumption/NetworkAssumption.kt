@file:Suppress("MagicNumber")

package reactivecircus.releaseprobe.testing.assumption

import org.koin.core.KoinComponent
import org.koin.core.inject
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
