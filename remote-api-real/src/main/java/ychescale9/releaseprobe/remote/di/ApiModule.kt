package ychescale9.releaseprobe.remote.di

import com.squareup.moshi.Moshi
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import ychescale9.releaseprobe.remote.BuildConfig
import ychescale9.releaseprobe.remote.BuildConfig.NETWORK_TIMEOUT_SECONDS
import ychescale9.releaseprobe.remote.artifact.adapter.ArtifactAdapter
import ychescale9.releaseprobe.remote.artifact.adapter.ArtifactGroupAdapter
import ychescale9.releaseprobe.remote.artifact.api.GoogleMavenService
import ychescale9.releaseprobe.remote.artifact.interceptor.GoogleMavenResponseInterceptor
import ychescale9.releaseprobe.remote.extension.build

val apiModule = module {

    single {
        Moshi.Builder()
                .add(ArtifactGroupAdapter())
                .add(ArtifactAdapter())
                .build()
    }

    single {
        OkHttpClient.Builder().build {
            connectTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            writeTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            readTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            // add interceptor for converting xml response to json
            addInterceptor(GoogleMavenResponseInterceptor())
            // add logging interceptor
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        }
    }

    single {
        Retrofit.Builder().build {
            baseUrl(BuildConfig.API_BASE_URL)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(MoshiConverterFactory.create(get()))
            client(get())
        }
    }

    single<GoogleMavenService> {
        get<Retrofit>().create(GoogleMavenService::class.java)
    }
}
