package ychescale9.releaseprobe.persistence.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import ychescale9.releaseprobe.persistence.BuildConfig
import ychescale9.releaseprobe.persistence.ReleaseProbeDatabase

@Module
object PersistenceModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideDatabase(context: Context): ReleaseProbeDatabase {
        return Room.databaseBuilder(
                context,
                ReleaseProbeDatabase::class.java,
                BuildConfig.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideArtifactCollectionDao(db: ReleaseProbeDatabase) = db.artifactCollectionDao()

    @Provides
    @Singleton
    @JvmStatic
    fun provideArtifactGroupDao(db: ReleaseProbeDatabase) = db.artifactGroupDao()

    @Provides
    @Singleton
    @JvmStatic
    fun provideArtifactDao(db: ReleaseProbeDatabase) = db.artifactDao()
}
