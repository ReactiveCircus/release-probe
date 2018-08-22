package ychescale9.releaseprobe.data.di

import com.nytimes.android.external.store3.base.impl.BarCode
import com.nytimes.android.external.store3.base.impl.MemoryPolicy
import com.nytimes.android.external.store3.base.impl.StalePolicy
import com.nytimes.android.external.store3.base.impl.room.StoreRoom
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import ychescale9.releaseprobe.data.BuildConfig
import ychescale9.releaseprobe.data.artifact.fetcher.ArtifactGroupsWithArtifactsFetcher
import ychescale9.releaseprobe.data.artifact.persister.ArtifactGroupsWithArtifactsPersister
import ychescale9.releaseprobe.data.artifact.repository.ArtifactRepositoryImpl
import ychescale9.releaseprobe.data.artifactcollection.repository.ArtifactCollectionRepositoryImpl
import ychescale9.releaseprobe.domain.artifact.model.ArtifactGroup
import ychescale9.releaseprobe.domain.artifact.repository.ArtifactRepository
import ychescale9.releaseprobe.domain.artifactcollection.repository.ArtifactCollectionRepository

@Module
object DataModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideArtifactGroupsWithArtifactsStore(
        fetcher: ArtifactGroupsWithArtifactsFetcher,
        persister: ArtifactGroupsWithArtifactsPersister
    ): StoreRoom<List<ArtifactGroup>, BarCode> {
        return StoreRoom.from(
                fetcher,
                persister,
                StalePolicy.NETWORK_BEFORE_STALE,
                MemoryPolicy.builder()
                        .setExpireAfterWrite(BuildConfig.STORE_EXPIRY_DURATION_HOURS)
                        .setExpireAfterTimeUnit(TimeUnit.HOURS)
                        .build()
        )
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideArtifactCollectionRepository(
        artifactCollectionRepository: ArtifactCollectionRepositoryImpl
    ): ArtifactCollectionRepository {
        return artifactCollectionRepository
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideArtifactRepository(artifactRepository: ArtifactRepositoryImpl): ArtifactRepository {
        return artifactRepository
    }
}
