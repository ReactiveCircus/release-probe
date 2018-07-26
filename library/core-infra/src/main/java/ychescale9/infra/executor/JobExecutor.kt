package ychescale9.infra.executor

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class JobExecutor : ThreadExecutor {

    private val numberOfCores = Runtime.getRuntime().availableProcessors()

    private val corePoolSize = numberOfCores + 1
    private val maxPoolSize = numberOfCores * 2 + 1

    private val keepAliveTime: Long = 30
    private val keepAliveTimeUnit = TimeUnit.SECONDS

    private val threadPoolExecutor: ThreadPoolExecutor

    init {
        threadPoolExecutor = ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, keepAliveTimeUnit, LinkedBlockingQueue())
    }

    override fun execute(command: Runnable) {
        this.threadPoolExecutor.execute(command)
    }
}
