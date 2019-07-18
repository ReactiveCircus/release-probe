package reactivecircus.releaseprobe.testing.di

import android.os.AsyncTask
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module
import reactivecircus.blueprint.threading.coroutines.SchedulerProvider
import reactivecircus.releaseprobe.browsecollections.di.browseCollectionsModule
import reactivecircus.releaseprobe.core.util.AnimationHelper
import reactivecircus.releaseprobe.data.di.dataModule
import reactivecircus.releaseprobe.di.settingsModule
import reactivecircus.releaseprobe.domain.di.domainModule
import reactivecircus.releaseprobe.feeds.di.feedsModule
import reactivecircus.releaseprobe.persistence.di.persistenceModule
import reactivecircus.releaseprobe.testing.helper.ScreenTestAnimationHelper
import reactivecircus.releaseprobe.watchlist.di.watchlistModule
import reactivecircus.releaseprobe.work.di.backgroundWorkModule

val testAppModule = module {

    single {
        // use AsyncTask.THREAD_POOL_EXECUTOR for io scheduler
        // as Espresso by default waits for async tasks to complete
        SchedulerProvider(
            io = Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR),
            computation = Schedulers.computation(),
            ui = AndroidSchedulers.mainThread()
        )
    }

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
