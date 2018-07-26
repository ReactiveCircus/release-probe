package ychescale9.releaseprobe.testing.di.module

import dagger.Module
import dagger.Provides
import ychescale9.infra.executor.PostExecutionThread
import ychescale9.infra.executor.ThreadExecutor
import ychescale9.infra.executor.UiThread
import ychescale9.infra.rx.SchedulerProvider
import ychescale9.infra.util.Clock
import ychescale9.infra.util.RealClock
import ychescale9.releaseprobe.testing.helper.ScreenTestJobExecutor
import ychescale9.releaseprobe.testing.helper.ScreenTestSchedulerProvider
import javax.inject.Singleton

@Module
object TestAppModule {

    /**
     * Provides an implementation of ThreadExecutor for testing
     * @return
     */
    @Provides
    @Singleton
    @JvmStatic
    fun provideThreadExecutor(): ThreadExecutor = ScreenTestJobExecutor()

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
    fun provideSchedulerProvider(): SchedulerProvider = ScreenTestSchedulerProvider()

    @Provides
    @Singleton
    @JvmStatic
    fun provideClock(): Clock = RealClock()
}