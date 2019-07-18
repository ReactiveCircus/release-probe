package reactivecircus.releaseprobe.browsecollections

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import reactivecircus.blueprint.interactor.EmptyParams
import timber.log.Timber
import reactivecircus.releaseprobe.domain.artifactcollection.model.ArtifactCollection
import reactivecircus.releaseprobe.domain.artifactcollection.interactor.GetOrCreateArtifactCollections

class ArtifactCollectionsViewModel(
    getOrCreateArtifactCollections: GetOrCreateArtifactCollections
) : ViewModel() {

    val artifactCollectionsLiveData = MutableLiveData<List<ArtifactCollection>>()

    private val disposable = CompositeDisposable()

    init {
        disposable += getOrCreateArtifactCollections.buildObservable(EmptyParams)
                .subscribeBy(
                        onNext = {
                            artifactCollectionsLiveData.value = it
                        },
                        onError = {
                            Timber.e(it, "Error getting artifact collections.")
                        }
                )
    }

    override fun onCleared() {
        disposable.clear()
    }
}
