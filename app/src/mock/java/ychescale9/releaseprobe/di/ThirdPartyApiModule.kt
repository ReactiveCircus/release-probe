package ychescale9.releaseprobe.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import ychescale9.analytics.AnalyticsApi
import ychescale9.analytics.NoOpAnalyticsApi

@Module
object ThirdPartyApiModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideAnalyticsApi(): AnalyticsApi = NoOpAnalyticsApi()
}
