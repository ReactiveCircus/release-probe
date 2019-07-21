package reactivecircus.releaseprobe

import android.annotation.SuppressLint
import android.app.Application
import com.bugsnag.android.Bugsnag
import com.bugsnag.android.Client
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import reactivecircus.analytics.AnalyticsApi
import reactivecircus.bugsnag.BugsnagTree
import reactivecircus.releaseprobe.di.modules
import timber.log.Timber

@SuppressLint("Registered")
open class ReleaseProbeApp : Application() {

    private lateinit var bugsnagClient: Client

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

        // initialize Bugsnag
        if (BuildConfig.ENABLE_BUGSNAG) {
            bugsnagClient = Bugsnag.init(this).apply {
                setReleaseStage(BuildConfig.BUILD_TYPE)
            }
        }

        // initialize Timber
        initializeTimber()

        // initialize analytics api
        analyticsApi.setEnableAnalytics(BuildConfig.ENABLE_ANALYTICS)
    }

    protected open fun initializeTimber() {
        val tree = BugsnagTree(bugsnagClient)
        Timber.plant(tree)
        bugsnagClient.beforeNotify { error ->
            tree.update(error)
            return@beforeNotify true
        }
    }
}
