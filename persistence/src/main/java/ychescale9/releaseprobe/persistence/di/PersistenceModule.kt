package ychescale9.releaseprobe.persistence.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ychescale9.releaseprobe.persistence.BuildConfig
import ychescale9.releaseprobe.persistence.ReleaseProbeDatabase

val persistenceModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            ReleaseProbeDatabase::class.java,
            BuildConfig.DATABASE_NAME
        ).build()
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
