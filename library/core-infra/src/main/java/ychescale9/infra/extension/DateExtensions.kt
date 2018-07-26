package ychescale9.infra.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * Converts the timestamp to a formatted String
 */
fun Long.toFormattedDateString(pattern: String): String {
    return SimpleDateFormat(pattern, Locale.getDefault()).apply {
        timeZone = TimeZone.getDefault()
    }.format(Date(this))
}
