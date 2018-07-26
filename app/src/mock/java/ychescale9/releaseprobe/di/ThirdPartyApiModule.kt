package ychescale9.releaseprobe.di

import dagger.Module
import dagger.Provides
import ychescale9.analytics.AnalyticsApi
import ychescale9.analytics.NoOpAnalyticsApi
import javax.inject.Singleton

@Module
object ThirdPartyApiModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideAnalyticsApi(): AnalyticsApi = NoOpAnalyticsApi()
}
