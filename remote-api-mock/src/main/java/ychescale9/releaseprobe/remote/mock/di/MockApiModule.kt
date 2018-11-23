package ychescale9.releaseprobe.remote.mock.di

import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import ychescale9.releaseprobe.remote.artifact.api.GoogleMavenService
import ychescale9.releaseprobe.remote.extension.build
import ychescale9.releaseprobe.remote.mock.BuildConfig.NETWORK_TIMEOUT_SECONDS
import ychescale9.releaseprobe.remote.mock.artifact.api.MockGoogleMavenService

private const val MOCK_SERVER_PORT = 5000
private const val DUMMY_URL = "http://localhost:$MOCK_SERVER_PORT/"

val mockApiModule = module {

    single {
        OkHttpClient.Builder().build {
            callTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            // add logging interceptor
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        }
    }

    single {
        Retrofit.Builder().build {
            baseUrl(DUMMY_URL)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            client(get())
        }
    }

    single {
        NetworkBehavior.create().apply {
            // make sure behavior is deterministic
            setVariancePercent(0)
            // no delay by default
            setDelay(0, TimeUnit.MILLISECONDS)
            // no failure by default
            setFailurePercent(0)
        }
    }

    single<GoogleMavenService> {
        MockGoogleMavenService(MockRetrofit.Builder(get())
                .apply { networkBehavior(get()) }
                .let { builder -> builder.build().create(GoogleMavenService::class.java) }
        )
    }
}
