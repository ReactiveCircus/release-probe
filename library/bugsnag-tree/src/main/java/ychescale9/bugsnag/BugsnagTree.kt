package ychescale9.bugsnag

import android.util.Log
import android.util.Log.*
import com.bugsnag.android.Client
import com.bugsnag.android.Error
import com.bugsnag.android.Severity
import timber.log.Timber
import java.util.*

/**
 * A logging implementation which buffers the last 200 messages and notifies on error exceptions.
 */
class BugsnagTree(private val client: Client) : Timber.Tree() {

    // Adding one to the initial size accounts for the add before remove.
    private val buffer = ArrayDeque<String>(BUFFER_SIZE + 1)

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        // only log WARN, ERROR, and WTF
        return priority == Log.WARN || priority == Log.ERROR || priority == Log.ASSERT
    }

    override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) {
        val log = buildString(message.length + 16) {
            append(System.currentTimeMillis())
            append(' ')
            append(when (priority) {
                ERROR -> "E"
                WARN -> "W"
                INFO -> "I"
                else -> priority.toString()
            })
            append(' ')
            append(message)
        }

        synchronized(buffer) {
            buffer.addLast(log)
            if (buffer.size > BUFFER_SIZE) {
                buffer.removeFirst()
            }
        }

        if (throwable != null) {
            when (priority) {
                ERROR -> client.notify(throwable, Severity.ERROR)
                WARN -> client.notify(throwable, Severity.WARNING)
                INFO -> client.notify(throwable, Severity.INFO)
            }
        }
    }

    fun update(error: Error) {
        synchronized(buffer) {
            buffer.forEachIndexed { index, message ->
                error.addToTab("Log", String.format(Locale.getDefault(), "%03d", index), message)
            }
        }
    }

    companion object {
        private const val BUFFER_SIZE = 200
    }
}