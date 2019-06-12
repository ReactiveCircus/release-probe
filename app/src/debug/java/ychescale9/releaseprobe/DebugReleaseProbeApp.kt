package ychescale9.releaseprobe

import android.os.StrictMode
import timber.log.Timber

class DebugReleaseProbeApp : ReleaseProbeApp() {

    override fun onCreate() {
        super.onCreate()

        // turn on strict mode for Debug build
        StrictMode.enableDefaults()
    }

    override fun initializeTimber() {
        Timber.plant(DebugTree())
    }
}
