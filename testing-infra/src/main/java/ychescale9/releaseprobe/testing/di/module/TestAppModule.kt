package ychescale9.releaseprobe.testing.di.module

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import ychescale9.infra.SchedulerProvider
import ychescale9.infra.util.Clock
import ychescale9.infra.util.RealClock
import ychescale9.releaseprobe.testing.helper.ScreenTestSchedulerProvider

@Module
object TestAppModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideSchedulerProvider(): SchedulerProvider = ScreenTestSchedulerProvider()

    @Provides
    @Singleton
    @JvmStatic
    fun provideClock(): Clock = RealClock()
}
