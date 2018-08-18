package ychescale9.releaseprobe.remote.di

import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import ychescale9.releaseprobe.remote.BuildConfig.NETWORK_TIMEOUT_SECONDS
import ychescale9.releaseprobe.remote.artifact.api.GoogleMavenService
import ychescale9.releaseprobe.remote.artifact.api.MockGoogleMavenService
import ychescale9.releaseprobe.remote.extension.build

@Module
object ApiModule {

    private const val MOCK_SERVER_PORT = 5000
    private const val DUMMY_URL = "http://localhost:$MOCK_SERVER_PORT/"

    @Provides
    @Singleton
    @JvmStatic
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build {
            connectTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            writeTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            readTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            // add logging interceptor
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        }
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().build {
            baseUrl(DUMMY_URL)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            client(client)
        }
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideNetworkBehavior(): NetworkBehavior {
        return NetworkBehavior.create().apply {
            // make sure behavior is deterministic
            setVariancePercent(0)
            // no delay by default
            setDelay(0, TimeUnit.MILLISECONDS)
            // no failure by default
            setFailurePercent(0)
        }
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideGoogleMavenService(behavior: NetworkBehavior, retrofit: Retrofit): GoogleMavenService {
        return MockGoogleMavenService(MockRetrofit.Builder(retrofit)
                .apply { networkBehavior(behavior) }
                .let { it.build().create(GoogleMavenService::class.java) }
        )
    }
}
