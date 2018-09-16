package ychescale9.releaseprobe

import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import ychescale9.releaseprobe.testing.ScreenTestApp
import ychescale9.releaseprobe.testing.di.testModules

class MainTestApp : ScreenTestApp() {

    private val mainModule = module(createOnStart = true) {
        // TODO
    }

    override fun loadKoinModules(): List<Module> {
        return testModules + mainModule
    }
}
