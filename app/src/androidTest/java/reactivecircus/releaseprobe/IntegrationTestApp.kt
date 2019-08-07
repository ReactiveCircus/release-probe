package reactivecircus.releaseprobe

import org.koin.core.module.Module
import org.koin.dsl.module
import reactivecircus.releaseprobe.core.AppNavigator
import reactivecircus.releaseprobe.di.uiModules
import reactivecircus.releaseprobe.testing.BaseTestApp

class IntegrationTestApp : BaseTestApp() {

    override fun loadUiModules(): List<Module> {
        return uiModules
    }

    /**
     * Use the real [ReleaseProbeAppNavigator] for integration test.
     */
    override fun loadNavigatorModule(): Module {
        return module {
            single<AppNavigator> { ReleaseProbeAppNavigator() }
        }
    }
}
