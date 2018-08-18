package ychescale9.releaseprobe.browse

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import ychescale9.infra.extension.getViewModel

@Module
abstract class BrowseBuilder {

    @ContributesAndroidInjector(modules = [BrowseModule::class])
    abstract fun bindBrowseFragment(): BrowseFragment
}

@Module
internal object BrowseModule {

    @Provides
    @JvmStatic
    fun provideBrowseViewModel(
        fragment: BrowseFragment
    ): ArtifactCollectionsViewModel {
        return fragment.getViewModel {
            ArtifactCollectionsViewModel()
        }
    }
}
