package ychescale9.releaseprobe

import leakcanary.LeakCanary
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import ychescale9.releaseprobe.testing.ScreenTestApp
import ychescale9.releaseprobe.testing.di.testModules

class MainTestApp : ScreenTestApp() {

    override fun onCreate() {
        super.onCreate()
        LeakCanary.config = LeakCanary.config.copy(dumpHeap = false)
    }


    private val mainModule = module(createOnStart = true) {
        // TODO
    }

    override fun loadKoinModules(): List<Module> {
        return testModules + mainModule
    }
}
