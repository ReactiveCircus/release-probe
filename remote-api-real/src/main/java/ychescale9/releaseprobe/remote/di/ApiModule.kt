package ychescale9.releaseprobe.remote.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import ychescale9.releaseprobe.remote.BuildConfig
import ychescale9.releaseprobe.remote.BuildConfig.NETWORK_TIMEOUT_SECONDS
import ychescale9.releaseprobe.remote.api.GoogleMavenService
import ychescale9.releaseprobe.remote.extension.build

@Module
object ApiModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideMoshi() = Moshi.Builder().build()

    @Provides
    @Singleton
    @JvmStatic
    fun provideOkHttpClient(moshi: Moshi): OkHttpClient {
        return OkHttpClient.Builder().build {
            connectTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            writeTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            readTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            // add interceptor for converting xml response to json
            // TODO addInterceptor(ApiResponseInterceptor(...))
            // add logging interceptor
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        }
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder().build {
            baseUrl(BuildConfig.API_BASE_URL)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(MoshiConverterFactory.create(moshi))
            client(client)
        }
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideGoogleMavenService(retrofit: Retrofit): GoogleMavenService {
        return retrofit.create(GoogleMavenService::class.java)
    }
}
