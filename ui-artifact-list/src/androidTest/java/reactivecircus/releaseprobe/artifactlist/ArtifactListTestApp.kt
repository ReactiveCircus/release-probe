package reactivecircus.releaseprobe.artifactlist

import org.koin.core.module.Module
import reactivecircus.releaseprobe.artifactlist.di.artifactListModule
import reactivecircus.releaseprobe.testing.BaseTestApp

class ArtifactListTestApp : BaseTestApp() {

    override fun loadUiModules(): List<Module> {
        return listOf(artifactListModule)
    }
}
