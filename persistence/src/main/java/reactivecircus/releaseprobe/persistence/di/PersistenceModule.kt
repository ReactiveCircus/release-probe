package reactivecircus.releaseprobe.persistence.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import reactivecircus.releaseprobe.persistence.BuildConfig
import reactivecircus.releaseprobe.persistence.DatabaseTransactionRunner
import reactivecircus.releaseprobe.persistence.ReleaseProbeDatabase
import reactivecircus.releaseprobe.persistence.RoomTransactionRunner

val persistenceModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            ReleaseProbeDatabase::class.java,
            BuildConfig.DATABASE_NAME
        ).build()
    }

    single<DatabaseTransactionRunner> {
        RoomTransactionRunner(get<ReleaseProbeDatabase>())
    }

    single {
        get<ReleaseProbeDatabase>().artifactCollectionDao()
    }

    single {
        get<ReleaseProbeDatabase>().artifactGroupDao()
    }

    single {
        get<ReleaseProbeDatabase>().artifactDao()
    }
}
