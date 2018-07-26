package ychescale9.infra.executor

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute out of the UI thread.
 */
import java.util.concurrent.Executor

interface ThreadExecutor : Executor
