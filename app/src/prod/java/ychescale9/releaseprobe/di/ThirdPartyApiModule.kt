package ychescale9.releaseprobe.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import ychescale9.analytics.AnalyticsApi
import ychescale9.analytics.FirebaseAnalyticsApi

@Module
object ThirdPartyApiModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideAnalyticsApi(context: Context): AnalyticsApi = FirebaseAnalyticsApi(context)
}
