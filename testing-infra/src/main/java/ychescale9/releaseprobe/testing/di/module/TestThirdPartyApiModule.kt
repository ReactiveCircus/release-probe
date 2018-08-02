package ychescale9.releaseprobe.testing.di.module

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import ychescale9.analytics.AnalyticsApi
import ychescale9.analytics.NoOpAnalyticsApi

@Module
object TestThirdPartyApiModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideAnalyticsApi(): AnalyticsApi = NoOpAnalyticsApi()
}
