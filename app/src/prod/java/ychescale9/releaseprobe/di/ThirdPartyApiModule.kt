package ychescale9.releaseprobe.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ychescale9.analytics.AnalyticsApi
import ychescale9.analytics.FirebaseAnalyticsApi
import javax.inject.Singleton

@Module
object ThirdPartyApiModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideAnalyticsApi(context: Context): AnalyticsApi = FirebaseAnalyticsApi(context)
}
