package ychescale9.releaseprobe.data.di

import com.nytimes.android.external.store3.base.impl.BarCode
import com.nytimes.android.external.store3.base.impl.MemoryPolicy
import com.nytimes.android.external.store3.base.impl.StalePolicy
import com.nytimes.android.external.store3.base.impl.room.StoreRoom
import java.util.concurrent.TimeUnit
import org.koin.dsl.module.module
import org.koin.experimental.builder.create
import org.koin.experimental.builder.single
import ychescale9.releaseprobe.data.BuildConfig
import ychescale9.releaseprobe.data.artifact.fetcher.ArtifactGroupsWithArtifactsFetcher
import ychescale9.releaseprobe.data.artifact.persister.ArtifactGroupsWithArtifactsPersister
import ychescale9.releaseprobe.data.artifact.repository.ArtifactRepositoryImpl
import ychescale9.releaseprobe.data.artifactcollection.DefaultArtifactCollections
import ychescale9.releaseprobe.data.artifactcollection.repository.ArtifactCollectionRepositoryImpl
import ychescale9.releaseprobe.domain.artifact.model.ArtifactGroup
import ychescale9.releaseprobe.domain.artifact.repository.ArtifactRepository
import ychescale9.releaseprobe.domain.artifactcollection.repository.ArtifactCollectionRepository

val dataModule = module {

    single {
        create<ArtifactGroupsWithArtifactsFetcher>()
    }

    single {
        create<ArtifactGroupsWithArtifactsPersister>()
    }

    single<StoreRoom<List<ArtifactGroup>, BarCode>> {
        val fetcher: ArtifactGroupsWithArtifactsFetcher = get()
        val persister: ArtifactGroupsWithArtifactsPersister = get()

        StoreRoom.from(
                fetcher,
                persister,
                StalePolicy.NETWORK_BEFORE_STALE,
                MemoryPolicy.builder()
                        .setExpireAfterWrite(BuildConfig.STORE_EXPIRY_DURATION_HOURS)
                        .setExpireAfterTimeUnit(TimeUnit.HOURS)
                        .build()
        )
    }

    single<DefaultArtifactCollections>()

    single<ArtifactCollectionRepository> {
        create<ArtifactCollectionRepositoryImpl>()
    }

    single<ArtifactRepository> {
        create<ArtifactRepositoryImpl>()
    }
}
