package reactivecircus.releaseprobe.browsecollections.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import reactivecircus.releaseprobe.browsecollections.ArtifactCollectionsViewModel

val browseCollectionsModule = module {

    viewModel {
        // TODO
        ArtifactCollectionsViewModel(
            get()
        )
    }
}
