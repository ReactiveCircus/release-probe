package ychescale9.releaseprobe.testing.di

import org.koin.dsl.module.module
import ychescale9.analytics.AnalyticsApi
import ychescale9.analytics.noop.NoOpAnalyticsApi

val testThirdPartyApiModule = module {
    single<AnalyticsApi> { NoOpAnalyticsApi() }
}
