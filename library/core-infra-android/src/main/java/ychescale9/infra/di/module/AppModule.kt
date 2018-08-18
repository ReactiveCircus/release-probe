package ychescale9.infra.di.module

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import ychescale9.infra.SchedulerProvider
import ychescale9.infra.rx.AndroidSchedulerProvider
import ychescale9.infra.util.Clock
import ychescale9.infra.util.RealClock

@Module
object AppModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideSchedulerProvider(): SchedulerProvider = AndroidSchedulerProvider()

    @Provides
    @Singleton
    @JvmStatic
    fun provideClock(): Clock = RealClock()
}
