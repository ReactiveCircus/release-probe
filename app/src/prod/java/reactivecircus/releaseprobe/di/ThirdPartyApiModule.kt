package reactivecircus.releaseprobe.di

import org.koin.dsl.module
import reactivecircus.analytics.AnalyticsApi
import reactivecircus.analytics.firebase.FirebaseAnalyticsApi

val thirdPartyApiModule = module {
    single<AnalyticsApi> { FirebaseAnalyticsApi }
}
