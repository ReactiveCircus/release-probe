package ychescale9.releaseprobe.testing

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber
import ychescale9.analytics.AnalyticsApi
import ychescale9.releaseprobe.testing.di.component.DaggerScreenTestAppComponent
import ychescale9.releaseprobe.testing.di.component.ScreenTestAppComponent
import javax.inject.Inject

open class ScreenTestApp : Application(), HasActivityInjector {

    private val testAppComponent: ScreenTestAppComponent by lazy {
        loadTestAppComponent()
    }

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var analyticsApi: AnalyticsApi

    override fun onCreate() {
        super.onCreate()

        // inject dependencies required for initialization
        testAppComponent.inject(this)

        // initialize Timber
        Timber.plant(TestDebugTree())

        // initialize analytics api (disable)
        analyticsApi.setEnableAnalytics(false)

        // set up global uncaught error handler for RxJava
        setUpRxJavaUncaughtErrorHandler()
    }

    override fun activityInjector() = dispatchingActivityInjector

    protected open fun loadTestAppComponent(): ScreenTestAppComponent {
        return DaggerScreenTestAppComponent.builder()
                .testApp(this)
                .build()
    }

    fun testAppComponent(): ScreenTestAppComponent {
        return testAppComponent
    }

    private fun setUpRxJavaUncaughtErrorHandler() {
        RxJavaPlugins.setErrorHandler { throwable -> Timber.w(throwable, "Uncaught RxJava error.") }
    }
}
