package reactivecircus.releaseprobe.artifactlist.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import reactivecircus.releaseprobe.artifactlist.ArtifactListViewModel
import timber.log.Timber

val artifactListModule = module {

    viewModel { (collectionKey: String) ->
        // TODO
        Timber.d("Artifact Collection: $collectionKey")
        ArtifactListViewModel()
    }
}
