package ychescale9.releaseprobe

import android.app.Activity
import android.app.Application
import com.bugsnag.android.Bugsnag
import com.bugsnag.android.Client
import com.squareup.leakcanary.LeakCanary
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber
import ychescale9.analytics.AnalyticsApi
import ychescale9.bugsnag.BugsnagTree
import ychescale9.releaseprobe.di.DaggerReleaseProbeAppComponent
import ychescale9.releaseprobe.di.ReleaseProbeAppComponent
import javax.inject.Inject

open class ReleaseProbeApp : Application(), HasActivityInjector {

    private val appComponent: ReleaseProbeAppComponent by lazy {
        DaggerReleaseProbeAppComponent.builder()
                .app(this)
                .build()
    }

    private lateinit var bugsnagClient: Client

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var analyticsApi: AnalyticsApi

    override fun onCreate() {
        super.onCreate()

        // inject dependencies required for initialization
        appComponent.inject(this)

        // initialize Bugsnag
        if (BuildConfig.ENABLE_BUGSNAG) {
            bugsnagClient = Bugsnag.init(this).apply {
                setReleaseStage(BuildConfig.BUILD_TYPE)
            }
        }

        // initialize Timber
        initializeTimber()

        // initialize leak detection
        if (LeakCanary.isInAnalyzerProcess(this)) return
        installLeakCanary()

        // initialize analytics api
        analyticsApi.setEnableAnalytics(BuildConfig.ENABLE_ANALYTICS)

        // set up global uncaught error handler for RxJava
        setUpRxJavaUncaughtErrorHandler()
    }

    override fun activityInjector() = dispatchingActivityInjector

    protected open fun installLeakCanary() {
        // no-op, LeakCanary is disabled in production
    }

    protected open fun initializeTimber() {
        val tree = BugsnagTree(bugsnagClient)
        Timber.plant(tree)
        bugsnagClient.beforeNotify {
            tree.update(it)
            return@beforeNotify true
        }
    }

    private fun setUpRxJavaUncaughtErrorHandler() {
        RxJavaPlugins.setErrorHandler { throwable -> Timber.w(throwable, "Uncaught RxJava error.") }
    }
}
