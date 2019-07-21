package reactivecircus.releaseprobe.browsecollections

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import reactivecircus.blueprint.interactor.EmptyParams
import reactivecircus.releaseprobe.domain.artifactcollection.interactor.StreamArtifactCollections
import reactivecircus.releaseprobe.domain.artifactcollection.model.ArtifactCollection
import timber.log.Timber

@ExperimentalCoroutinesApi
class ArtifactCollectionsViewModel(
    streamArtifactCollections: StreamArtifactCollections
) : ViewModel() {

    val artifactCollectionsLiveData = MutableLiveData<List<ArtifactCollection>>()

    init {
        streamArtifactCollections.buildFlow(EmptyParams)
            .onEach {
                artifactCollectionsLiveData.value = it
            }
            .catch {
                Timber.e(it, "State machine stream terminated.")
            }
            .launchIn(viewModelScope)
    }
}
