package ychescale9.releaseprobe.di

import org.koin.dsl.module
import ychescale9.infra.SchedulerProvider
import ychescale9.infra.android.rx.AndroidSchedulerProvider
import ychescale9.infra.util.Clock
import ychescale9.infra.util.RealClock
import ychescale9.releaseprobe.browsecollections.di.browseCollectionsModule
import ychescale9.releaseprobe.core.util.AnimationHelper
import ychescale9.releaseprobe.data.di.dataModule
import ychescale9.releaseprobe.domain.di.domainModule
import ychescale9.releaseprobe.feeds.di.feedsModule
import ychescale9.releaseprobe.persistence.di.persistenceModule
import ychescale9.releaseprobe.watchlist.di.watchlistModule
import ychescale9.releaseprobe.work.di.backgroundWorkModule

val appModule = module {

    single<SchedulerProvider> { AndroidSchedulerProvider() }

    single<Clock> { RealClock() }

    single { AnimationHelper() }
}

val featureModules = listOf(
    feedsModule,
    watchlistModule,
    browseCollectionsModule,
    settingsModule
)

val modules = featureModules + listOf(
    appModule,
    backgroundWorkModule,
    domainModule,
    dataModule,
    persistenceModule,
    apiModule,
    thirdPartyApiModule
)
