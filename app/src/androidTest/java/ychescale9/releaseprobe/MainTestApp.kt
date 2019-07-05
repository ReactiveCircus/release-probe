package ychescale9.releaseprobe

import org.koin.core.module.Module
import org.koin.dsl.module
import ychescale9.releaseprobe.testing.ScreenTestApp
import ychescale9.releaseprobe.testing.di.testModules

class MainTestApp : ScreenTestApp() {

    private val mainModule = module {
        // TODO
    }

    override fun loadKoinModules(): List<Module> {
        return testModules + mainModule
    }
}
