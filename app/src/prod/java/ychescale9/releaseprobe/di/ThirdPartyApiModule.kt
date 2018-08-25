package ychescale9.releaseprobe.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import ychescale9.analytics.AnalyticsApi
import ychescale9.analytics.FirebaseAnalyticsApi

val thirdPartyApiModule = module {
    single<AnalyticsApi> { FirebaseAnalyticsApi(androidContext()) }
}
