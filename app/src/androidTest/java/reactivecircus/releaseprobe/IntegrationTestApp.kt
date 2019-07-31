package reactivecircus.releaseprobe

import org.koin.core.module.Module
import reactivecircus.releaseprobe.di.uiModules
import reactivecircus.releaseprobe.testing.BaseTestApp

class IntegrationTestApp : BaseTestApp() {

    override fun loadUiModules(): List<Module> {
        return uiModules
    }
}
