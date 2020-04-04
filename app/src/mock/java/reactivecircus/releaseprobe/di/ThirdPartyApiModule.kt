package reactivecircus.releaseprobe.di

import org.koin.dsl.module
import reactivecircus.analytics.AnalyticsApi
import reactivecircus.analytics.noop.NoOpAnalyticsApi

val thirdPartyApiModule = module {
    single<AnalyticsApi> { NoOpAnalyticsApi }
}
