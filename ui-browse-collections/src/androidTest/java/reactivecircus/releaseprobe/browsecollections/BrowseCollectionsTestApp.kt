package reactivecircus.releaseprobe.browsecollections

import org.koin.core.module.Module
import reactivecircus.releaseprobe.browsecollections.di.browseCollectionsModule
import reactivecircus.releaseprobe.testing.BaseTestApp

class BrowseCollectionsTestApp : BaseTestApp() {

    override fun loadUiModules(): List<Module> {
        return listOf(browseCollectionsModule)
    }
}
