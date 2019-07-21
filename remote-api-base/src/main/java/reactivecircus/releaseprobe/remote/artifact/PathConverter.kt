package reactivecircus.releaseprobe.remote.artifact

/**
 * Convert artifact group ID to url path by replacing "." with "/".
 */
fun String.toPath() = replace(".", "/")
