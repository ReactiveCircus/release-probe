package ychescale9.releaseprobe.di

import org.koin.dsl.module
import ychescale9.analytics.AnalyticsApi
import ychescale9.analytics.noop.NoOpAnalyticsApi

val thirdPartyApiModule = module {
    single<AnalyticsApi> { NoOpAnalyticsApi() }
}
