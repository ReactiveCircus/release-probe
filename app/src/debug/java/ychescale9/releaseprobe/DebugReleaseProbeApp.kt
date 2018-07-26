package ychescale9.releaseprobe

import android.os.StrictMode
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

class DebugReleaseProbeApp : ReleaseProbeApp() {

    override fun onCreate() {
        super.onCreate()

        // turn on strict mode for Debug build
        StrictMode.enableDefaults()
    }

    override fun installLeakCanary() {
        LeakCanary.refWatcher(this)
                .listenerServiceClass(LeakUploadService::class.java)
                .buildAndInstall()
    }

    override fun initializeTimber() {
        Timber.plant(DebugTree())
    }
}
