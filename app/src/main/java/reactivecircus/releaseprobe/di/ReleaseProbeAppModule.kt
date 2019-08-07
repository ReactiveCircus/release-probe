package reactivecircus.releaseprobe.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import reactivecircus.blueprint.threading.coroutines.CoroutineDispatcherProvider
import reactivecircus.releaseprobe.ReleaseProbeAppNavigator
import reactivecircus.releaseprobe.artifactlist.di.artifactListModule
import reactivecircus.releaseprobe.browsecollections.di.browseCollectionsModule
import reactivecircus.releaseprobe.core.AppNavigator
import reactivecircus.releaseprobe.core.util.AnimationConfigs
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

    single { AnimationConfigs() }

    single<AppNavigator> {
        ReleaseProbeAppNavigator()
    }
}

val uiModules = listOf(
    feedsModule,
    watchlistModule,
    browseCollectionsModule,
    settingsModule,
    artifactListModule
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
