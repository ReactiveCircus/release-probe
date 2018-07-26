package ychescale9.infra.di.module

import dagger.Module
import dagger.Provides
import ychescale9.infra.executor.JobExecutor
import ychescale9.infra.executor.PostExecutionThread
import ychescale9.infra.executor.ThreadExecutor
import ychescale9.infra.executor.UiThread
import ychescale9.infra.rx.SchedulerProvider
import ychescale9.infra.util.Clock
import ychescale9.infra.util.RealClock
import javax.inject.Singleton

@Module
object AppModule {

    /**
     * Provides a background thread executor, which executes threads from a thread pool
     * @return
     */
    @Provides
    @Singleton
    @JvmStatic
    fun provideThreadExecutor(): ThreadExecutor = JobExecutor()

    /**
     * Provides main ui looper thread
     * @param uiThread
     * @return
     */
    @Provides
    @Singleton
    @JvmStatic
    fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread = uiThread

    @Provides
    @Singleton
    @JvmStatic
    fun provideSchedulerProvider() = SchedulerProvider()

    @Provides
    @Singleton
    @JvmStatic
    fun provideClock(): Clock = RealClock()
}