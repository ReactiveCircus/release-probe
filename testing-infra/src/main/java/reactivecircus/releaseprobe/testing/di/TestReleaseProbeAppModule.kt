package reactivecircus.releaseprobe.testing.di

import android.os.AsyncTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import org.koin.dsl.module
import reactivecircus.blueprint.threading.coroutines.CoroutineDispatcherProvider
import reactivecircus.releaseprobe.core.util.AnimationConfigs
import reactivecircus.releaseprobe.data.di.dataModule
import reactivecircus.releaseprobe.domain.di.domainModule
import reactivecircus.releaseprobe.persistence.di.persistenceModule
import reactivecircus.releaseprobe.testing.TestAnimationConfigs
import reactivecircus.releaseprobe.work.di.backgroundWorkModule

private val testAppModule = module {

    single {
        // TODO use proper io dispatcher https://github.com/Kotlin/kotlinx.coroutines/issues/242
        CoroutineDispatcherProvider(
            io = AsyncTask.THREAD_POOL_EXECUTOR.asCoroutineDispatcher(),
            computation = Dispatchers.Default,
            ui = Dispatchers.Main.immediate
        )
    }

    single<AnimationConfigs> { TestAnimationConfigs() }
}

val testModules = listOf(
    testAppModule,
    backgroundWorkModule,
    domainModule,
    dataModule,
    persistenceModule,
    testApiModule,
    testThirdPartyApiModule
)
