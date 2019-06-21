package ychescale9.releaseprobe.testing

import android.app.Application
import android.os.Looper
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.plugins.RxJavaPlugins
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import org.koin.core.module.Module
import timber.log.Timber
import ychescale9.analytics.AnalyticsApi
import ychescale9.releaseprobe.testing.di.testModules

open class ScreenTestApp : Application() {

    private val analyticsApi: AnalyticsApi by inject()

    override fun onCreate() {
        super.onCreate()

        // ask RxAndroid to use async main thread scheduler
        val asyncMainThreadScheduler = AndroidSchedulers.from(Looper.getMainLooper(), true)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { asyncMainThreadScheduler }

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

        // set up global uncaught error handler for RxJava
        setUpRxJavaUncaughtErrorHandler()
    }

    protected open fun loadKoinModules(): List<Module> {
        return testModules
    }

    private fun setUpRxJavaUncaughtErrorHandler() {
        RxJavaPlugins.setErrorHandler { throwable -> Timber.w(throwable, "Uncaught RxJava error.") }
    }
}
