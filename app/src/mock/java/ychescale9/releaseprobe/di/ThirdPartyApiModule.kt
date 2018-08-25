package ychescale9.releaseprobe.di

import org.koin.dsl.module.module
import ychescale9.analytics.AnalyticsApi
import ychescale9.analytics.NoOpAnalyticsApi

val thirdPartyApiModule = module {
    single<AnalyticsApi> { NoOpAnalyticsApi() }
}
