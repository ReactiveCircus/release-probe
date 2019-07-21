package reactivecircus.releaseprobe.persistence

import androidx.room.RoomDatabase
import androidx.room.withTransaction

class RoomTransactionRunner(
    private val database: RoomDatabase
) : DatabaseTransactionRunner {
    override suspend operator fun <T> invoke(block: suspend () -> T): T {
        return database.withTransaction {
            block()
        }
    }
}
