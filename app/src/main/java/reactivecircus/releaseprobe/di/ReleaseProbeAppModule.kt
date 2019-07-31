package reactivecircus.releaseprobe.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import reactivecircus.blueprint.threading.coroutines.CoroutineDispatcherProvider
import reactivecircus.releaseprobe.browsecollections.di.browseCollectionsModule
import reactivecircus.releaseprobe.core.util.AnimationHelper
import reactivecircus.releaseprobe.data.di.dataModule
import reactivecircus.releaseprobe.domain.di.domainModule
import reactivecircus.releaseprobe.feeds.di.feedsModule
import reactivecircus.releaseprobe.persistence.di.persistenceModule
import reactivecircus.releaseprobe.watchlist.di.watchlistModule
import reactivecircus.releaseprobe.work.di.backgroundWorkModule

private val appModule = module {

    single {
        CoroutineDispatcherProvider(
            io = Dispatchers.IO,
            computation = Dispatchers.Default,
            ui = Dispatchers.Main
        )
    }

    single { AnimationHelper() }
}

val uiModules = listOf(
    feedsModule,
    watchlistModule,
    browseCollectionsModule,
    settingsModule
)

val modules = uiModules + listOf(
    appModule,
    backgroundWorkModule,
    domainModule,
    dataModule,
    persistenceModule,
    apiModule,
    thirdPartyApiModule
)
