package reactivecircus.releaseprobe.testing

import android.app.Application
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import org.koin.core.module.Module
import reactivecircus.analytics.AnalyticsApi
import reactivecircus.releaseprobe.testing.di.testModules
import timber.log.Timber

open class ScreenTestApp : Application() {

    private val analyticsApi: AnalyticsApi by inject()

    override fun onCreate() {
        super.onCreate()

        // configure and install Koin modules
        startKoin {
            // logger (Timber)
            logger(EmptyLogger())

            // Android context
            androidContext(this@ScreenTestApp)

            // all modules
            modules(loadKoinModules())
        }

        // initialize Timber
        Timber.plant(TestDebugTree())

        // initialize analytics api (disable)
        analyticsApi.setEnableAnalytics(false)
    }

    protected open fun loadKoinModules(): List<Module> {
        return testModules
    }
}
