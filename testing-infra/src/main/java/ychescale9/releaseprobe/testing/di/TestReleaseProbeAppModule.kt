package ychescale9.releaseprobe.testing.di

import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.dsl.path.moduleName
import ychescale9.infra.SchedulerProvider
import ychescale9.infra.util.Clock
import ychescale9.infra.util.RealClock
import ychescale9.releaseprobe.browsecollections.ArtifactCollectionsViewModel
import ychescale9.releaseprobe.browsecollections.BrowseCollectionsFragment
import ychescale9.releaseprobe.data.di.dataModule
import ychescale9.releaseprobe.domain.di.domainModule
import ychescale9.releaseprobe.feeds.FeedsFragment
import ychescale9.releaseprobe.persistence.di.persistenceModule
import ychescale9.releaseprobe.remote.di.apiModule
import ychescale9.releaseprobe.settings.SettingsFragment
import ychescale9.releaseprobe.testing.helper.ScreenTestAnimationHelper
import ychescale9.releaseprobe.testing.helper.ScreenTestSchedulerProvider
import ychescale9.releaseprobe.util.AnimationHelper
import ychescale9.releaseprobe.watchlist.WatchlistFragment

val testAppModule = module {

    single<SchedulerProvider> { ScreenTestSchedulerProvider() }

    single<Clock> { RealClock() }

    single<AnimationHelper> { ScreenTestAnimationHelper() }
}

val viewModelModule = module {

    module(FeedsFragment::class.moduleName, true) {
        // TODO
    }

    module(WatchlistFragment::class.moduleName, true) {
        // TODO
    }

    module(BrowseCollectionsFragment::class.moduleName, true) {
        viewModel { create<ArtifactCollectionsViewModel>() }
    }

    module(SettingsFragment::class.moduleName, true) {
        // TODO
    }
}

val testModules = listOf(
        testAppModule,
        viewModelModule,
        domainModule,
        dataModule,
        persistenceModule,
        apiModule,
        testThirdPartyApiModule
)
