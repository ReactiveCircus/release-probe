package reactivecircus.releaseprobe.testing.di

import android.os.AsyncTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import org.koin.dsl.module
import reactivecircus.blueprint.threading.coroutines.CoroutineDispatcherProvider
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
        // TODO use proper io dispatcher https://github.com/Kotlin/kotlinx.coroutines/issues/242
        CoroutineDispatcherProvider(
            io = AsyncTask.THREAD_POOL_EXECUTOR.asCoroutineDispatcher(),
            computation = Dispatchers.Default,
            ui = Dispatchers.Main
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
