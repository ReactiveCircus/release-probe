package ychescale9.releaseprobe

import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import org.koin.dsl.path.moduleName
import ychescale9.releaseprobe.main.MainActivity
import ychescale9.releaseprobe.testing.ScreenTestApp
import ychescale9.releaseprobe.testing.di.testModules

class MainTestApp : ScreenTestApp() {

    private val mainModule = module(MainActivity::class.moduleName, true) {
        // TODO
    }

    override fun loadKoinModules(): List<Module> {
        return testModules + mainModule
    }
}
