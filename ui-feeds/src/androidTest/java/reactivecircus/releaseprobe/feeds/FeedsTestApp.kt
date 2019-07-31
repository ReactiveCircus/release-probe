package reactivecircus.releaseprobe.feeds

import org.koin.core.module.Module
import reactivecircus.releaseprobe.feeds.di.feedsModule
import reactivecircus.releaseprobe.testing.BaseTestApp

class FeedsTestApp : BaseTestApp() {

    override fun loadUiModules(): List<Module> {
        return listOf(feedsModule)
    }
}
