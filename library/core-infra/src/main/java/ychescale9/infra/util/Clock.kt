package ychescale9.infra.util

interface Clock {

    /**
     * Returns the current time in milliseconds UTC.
     * @return
     */
    val currentTimeMillis: Long
}
