package reactivecircus.releaseprobe.di

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module
import reactivecircus.blueprint.threading.coroutines.SchedulerProvider
import reactivecircus.releaseprobe.browsecollections.di.browseCollectionsModule
import reactivecircus.releaseprobe.core.util.AnimationHelper
import reactivecircus.releaseprobe.data.di.dataModule
import reactivecircus.releaseprobe.domain.di.domainModule
import reactivecircus.releaseprobe.feeds.di.feedsModule
import reactivecircus.releaseprobe.persistence.di.persistenceModule
import reactivecircus.releaseprobe.watchlist.di.watchlistModule
import reactivecircus.releaseprobe.work.di.backgroundWorkModule

val appModule = module {

    single {
        SchedulerProvider(
            io = Schedulers.io(),
            computation = Schedulers.computation(),
            ui = AndroidSchedulers.mainThread()
        )
    }

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
