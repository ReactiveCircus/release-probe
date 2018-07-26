package ychescale9.releaseprobe.testing.di.module

import dagger.Module
import dagger.Provides
import ychescale9.analytics.AnalyticsApi
import ychescale9.analytics.NoOpAnalyticsApi
import javax.inject.Singleton

@Module
object TestThirdPartyApiModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideAnalyticsApi(): AnalyticsApi = NoOpAnalyticsApi()
}