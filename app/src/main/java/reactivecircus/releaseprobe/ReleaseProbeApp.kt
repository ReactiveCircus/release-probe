package reactivecircus.releaseprobe

import android.annotation.SuppressLint
import android.app.Application
import com.bugsnag.android.Bugsnag
import com.bugsnag.android.Configuration
import com.bugsnag.android.OnErrorCallback
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import reactivecircus.analytics.AnalyticsApi
import reactivecircus.bugsnag.BugsnagTree
import reactivecircus.releaseprobe.di.modules
import timber.log.Timber

@SuppressLint("Registered")
open class ReleaseProbeApp : Application() {

    private val analyticsApi: AnalyticsApi by inject()

    override fun onCreate() {
        super.onCreate()

        // configure and install Koin modules
        startKoin {
            // logger (Timber)
            logger(TimberLogger())

            // Android context
            androidContext(this@ReleaseProbeApp)

            // all modules
            modules(modules)
        }

        // initialize Timber
        initializeTimber()

        // initialize analytics api
        analyticsApi.setEnableAnalytics(BuildConfig.ENABLE_ANALYTICS)
    }

    protected open fun initializeTimber() {
        val tree = BugsnagTree()
        Timber.plant(tree)

        // initialize Bugsnag
        if (BuildConfig.ENABLE_BUGSNAG) {
            val config = Configuration.load(this).apply {
                enabledReleaseStages = setOf(BuildConfig.BUILD_TYPE)
                enabledErrorTypes.ndkCrashes = false
                addOnError(OnErrorCallback { event ->
                    tree.update(event)
                    true
                })
            }
            Bugsnag.start(this, config)
        }
    }
}
