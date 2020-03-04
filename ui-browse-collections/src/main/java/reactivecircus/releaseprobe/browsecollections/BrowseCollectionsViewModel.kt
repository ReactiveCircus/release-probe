package reactivecircus.releaseprobe.browsecollections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import reactivecircus.blueprint.interactor.EmptyParams
import reactivecircus.releaseprobe.domain.artifactcollection.interactor.StreamArtifactCollections
import timber.log.Timber

@OptIn(ExperimentalCoroutinesApi::class)
class BrowseCollectionsViewModel(
    streamArtifactCollections: StreamArtifactCollections
) : ViewModel() {

    val artifactCollectionsLiveData = streamArtifactCollections.buildFlow(EmptyParams)
        .catch { Timber.e(it, "State machine stream terminated.") }
        .asLiveData()
}
