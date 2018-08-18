package ychescale9.releaseprobe.browse

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class ArtifactCollectionsViewModel : ViewModel() {

    private val disposable = CompositeDisposable()

    init {
        Timber.d("TODO")
    }

    override fun onCleared() {
        disposable.clear()
    }
}
