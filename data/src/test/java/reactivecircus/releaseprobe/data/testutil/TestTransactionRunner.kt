package reactivecircus.releaseprobe.data.testutil

import reactivecircus.releaseprobe.persistence.DatabaseTransactionRunner

object TestTransactionRunner : DatabaseTransactionRunner {

    override suspend fun <T> invoke(block: suspend () -> T): T = block()
}
