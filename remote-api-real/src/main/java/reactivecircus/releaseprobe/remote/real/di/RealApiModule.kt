package reactivecircus.releaseprobe.remote.real.di

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import reactivecircus.releaseprobe.remote.artifact.api.GoogleMavenService
import reactivecircus.releaseprobe.remote.real.BuildConfig
import reactivecircus.releaseprobe.remote.real.BuildConfig.NETWORK_TIMEOUT_SECONDS
import reactivecircus.releaseprobe.remote.real.artifact.adapter.ArtifactAdapter
import reactivecircus.releaseprobe.remote.real.artifact.adapter.ArtifactGroupAdapter
import reactivecircus.releaseprobe.remote.real.artifact.interceptor.GoogleMavenResponseInterceptor
import java.util.concurrent.TimeUnit

val realApiModule = module {

    single {
        Moshi.Builder()
            .add(ArtifactGroupAdapter())
            .add(ArtifactAdapter())
            .build()
    }

    single {
        OkHttpClient.Builder()
            .callTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            // add interceptor for converting xml response to json
            .addInterceptor(GoogleMavenResponseInterceptor())
            // add logging interceptor
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            })
            .build()
    }

    single {
        Retrofit.Builder().baseUrl(BuildConfig.API_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create<GoogleMavenService>()
    }
}
