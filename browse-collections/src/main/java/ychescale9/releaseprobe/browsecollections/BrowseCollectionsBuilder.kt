package ychescale9.releaseprobe.browsecollections

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import ychescale9.infra.extension.getViewModel

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
        fragment: BrowseCollectionsFragment
    ): ArtifactCollectionsViewModel {
        return fragment.getViewModel {
            ArtifactCollectionsViewModel()
        }
    }
}
