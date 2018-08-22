package ychescale9.releaseprobe.browsecollections

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import ychescale9.infra.extension.getViewModel
import ychescale9.releaseprobe.domain.artifactcollection.usecase.GetOrCreateArtifactCollections

@Module
abstract class BrowseCollectionsBuilder {

    @ContributesAndroidInjector(modules = [BrowseCollectionsModule::class])
    abstract fun bindBrowseCollectionsFragment(): BrowseCollectionsFragment
}

@Module
internal object BrowseCollectionsModule {

    @Provides
    @JvmStatic
    fun provideArtifactCollectionsViewModel(
        fragment: BrowseCollectionsFragment,
        getOrCreateArtifactCollections: GetOrCreateArtifactCollections
    ): ArtifactCollectionsViewModel {
        return fragment.getViewModel {
            ArtifactCollectionsViewModel(getOrCreateArtifactCollections)
        }
    }
}
