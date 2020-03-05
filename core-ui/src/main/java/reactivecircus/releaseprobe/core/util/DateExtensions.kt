package reactivecircus.releaseprobe.core.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * Converts the timestamp to a formatted String
 */
fun Long.toFormattedDateString(pattern: String): String {
    return SimpleDateFormat(pattern, Locale.ENGLISH).apply {
        timeZone = TimeZone.getDefault()
    }.format(Date(this))
}
