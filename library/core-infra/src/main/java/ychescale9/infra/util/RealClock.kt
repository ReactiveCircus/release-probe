package ychescale9.infra.util

class RealClock : Clock {

    override val currentTimeMillis: Long
        get() = System.currentTimeMillis()
}
