package ychescale9.infra.extension

/**
 * Convert a nullable value to the not null value, throwing an [IllegalArgumentException] if the [value] is null.
 */
fun <T : Any> T?.notNullOrThrow(): T {
    return requireNotNull(this)
}
