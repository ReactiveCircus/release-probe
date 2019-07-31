package reactivecircus.releaseprobe.watchlist

import org.koin.core.module.Module
import reactivecircus.releaseprobe.testing.BaseTestApp
import reactivecircus.releaseprobe.watchlist.di.watchlistModule

class WatchlistTestApp : BaseTestApp() {

    override fun loadUiModules(): List<Module> {
        return listOf(watchlistModule)
    }
}
