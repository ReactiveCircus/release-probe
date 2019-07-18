package reactivecircus.releaseprobe

import org.koin.core.module.Module
import org.koin.dsl.module
import reactivecircus.releaseprobe.testing.ScreenTestApp
import reactivecircus.releaseprobe.testing.di.testModules

class MainTestApp : ScreenTestApp() {

    private val mainModule = module {
        // TODO
    }

    override fun loadKoinModules(): List<Module> {
        return testModules + mainModule
    }
}
