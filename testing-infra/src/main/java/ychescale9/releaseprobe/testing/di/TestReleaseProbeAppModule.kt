package ychescale9.releaseprobe.testing.di

import org.koin.dsl.module
import ychescale9.infra.SchedulerProvider
import ychescale9.infra.util.Clock
import ychescale9.infra.util.RealClock
import ychescale9.releaseprobe.browsecollections.di.browseCollectionsModule
import ychescale9.releaseprobe.core.util.AnimationHelper
import ychescale9.releaseprobe.data.di.dataModule
import ychescale9.releaseprobe.domain.di.domainModule
import ychescale9.releaseprobe.feeds.di.feedsModule
import ychescale9.releaseprobe.persistence.di.persistenceModule
import ychescale9.releaseprobe.di.settingsModule
import ychescale9.releaseprobe.testing.helper.ScreenTestAnimationHelper
import ychescale9.releaseprobe.testing.helper.ScreenTestSchedulerProvider
import ychescale9.releaseprobe.watchlist.di.watchlistModule
import ychescale9.releaseprobe.work.di.backgroundWorkModule

val testAppModule = module {

    single<SchedulerProvider> { ScreenTestSchedulerProvider() }

    single<Clock> { RealClock() }

    single<AnimationHelper> { ScreenTestAnimationHelper() }
}

val featureModules = listOf(
    feedsModule,
    watchlistModule,
    browseCollectionsModule,
    settingsModule
)

val testModules = featureModules + listOf(
    testAppModule,
    backgroundWorkModule,
    domainModule,
    dataModule,
    persistenceModule,
    testApiModule,
    testThirdPartyApiModule
)
