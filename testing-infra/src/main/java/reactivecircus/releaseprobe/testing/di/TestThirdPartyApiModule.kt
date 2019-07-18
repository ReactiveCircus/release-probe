package reactivecircus.releaseprobe.testing.di

import org.koin.dsl.module
import reactivecircus.analytics.AnalyticsApi
import reactivecircus.analytics.noop.NoOpAnalyticsApi

val testThirdPartyApiModule = module {
    single<AnalyticsApi> { NoOpAnalyticsApi() }
}
