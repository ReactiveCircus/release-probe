package ychescale9.releaseprobe.browsecollections

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import ychescale9.infra.domain.EmptyParams
import ychescale9.releaseprobe.domain.artifactcollection.model.ArtifactCollection
import ychescale9.releaseprobe.domain.artifactcollection.usecase.GetOrCreateArtifactCollections

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
