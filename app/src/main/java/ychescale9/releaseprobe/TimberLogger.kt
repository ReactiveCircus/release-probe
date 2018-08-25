package ychescale9.releaseprobe

import org.koin.log.Logger
import timber.log.Timber

/**
 * Timber Logger for Koin.
 */
class TimberLogger : Logger {

    override fun debug(msg: String) {
        Timber.d(msg)
    }

    override fun err(msg: String) {
        Timber.e(msg)
    }

    override fun info(msg: String) {
        Timber.i(msg)
    }
}
