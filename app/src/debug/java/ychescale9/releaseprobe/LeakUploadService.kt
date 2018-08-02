package ychescale9.releaseprobe

import com.bugsnag.android.Bugsnag
import com.bugsnag.android.MetaData
import com.bugsnag.android.Severity
import com.squareup.leakcanary.AnalysisResult
import com.squareup.leakcanary.DisplayLeakService
import com.squareup.leakcanary.HeapDump
import java.io.PrintWriter
import java.io.StringWriter
import java.security.MessageDigest

/**
 * Upload LeakCanary trace as exception to Bugsnag.
 */
class LeakUploadService : DisplayLeakService() {

    override fun afterDefaultHandling(heapDump: HeapDump, result: AnalysisResult, leakInfo: String) {
        if (!result.leakFound || result.excludedLeak) {
            return
        }
        if (result.leakFound) {
            uploadLeakToServer(result, leakInfo)
        }
    }

    private fun uploadLeakToServer(result: AnalysisResult, leakInfo: String) {
        Bugsnag.getClient().apply {
            setSendThreads(false)
            beforeNotify { error ->
                // Bugsnag does smart grouping of exceptions, which we don't want for leak traces.
                // So instead we rely on the SHA-1 of the stacktrace, which has a low risk of collision.
                val exceptionAsString = StringWriter().let { sw ->
                    error.exception.printStackTrace(PrintWriter(sw))
                    sw.toString()
                }

                val uniqueHash = MessageDigest.getInstance("SHA-1")
                        .digest(exceptionAsString.toByteArray(Charsets.UTF_8))
                        .fold("") { str, byte -> str + "%02x".format(byte) }

                error.setGroupingHash(uniqueHash)
                true
            }

            val metadata = MetaData().apply {
                addToTab("LeakInfo", "LeakInfo", leakInfo)
            }

            notifyBlocking(result.leakTraceAsFakeException()) { report ->
                report.error?.apply {
                    severity = Severity.ERROR
                    metaData = metadata
                }
            }
        }
    }
}
