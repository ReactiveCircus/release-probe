package reactivecircus.releaseprobe.browsecollections.di

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import reactivecircus.releaseprobe.browsecollections.ArtifactCollectionsViewModel

@ExperimentalCoroutinesApi
val browseCollectionsModule = module {

    viewModel {
        // TODO
        ArtifactCollectionsViewModel(
            get()
        )
    }
}
