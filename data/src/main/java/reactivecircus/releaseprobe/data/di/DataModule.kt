package reactivecircus.releaseprobe.data.di

import com.nytimes.android.external.store3.base.impl.BarCode
import com.nytimes.android.external.store3.base.impl.MemoryPolicy
import com.nytimes.android.external.store3.base.impl.StalePolicy
import com.nytimes.android.external.store3.base.impl.room.StoreRoom
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module
import reactivecircus.releaseprobe.data.BuildConfig
import reactivecircus.releaseprobe.data.artifact.fetcher.ArtifactGroupsWithArtifactsFetcher
import reactivecircus.releaseprobe.data.artifact.persister.ArtifactGroupsWithArtifactsPersister
import reactivecircus.releaseprobe.data.artifact.repository.ArtifactRepositoryImpl
import reactivecircus.releaseprobe.data.artifactcollection.DefaultArtifactCollections
import reactivecircus.releaseprobe.data.artifactcollection.repository.ArtifactCollectionRepositoryImpl
import reactivecircus.releaseprobe.domain.artifact.model.ArtifactGroup
import reactivecircus.releaseprobe.domain.artifact.repository.ArtifactRepository
import reactivecircus.releaseprobe.domain.artifactcollection.repository.ArtifactCollectionRepository
import java.util.concurrent.TimeUnit

@UseExperimental(ExperimentalCoroutinesApi::class)
val dataModule = module {

    single {
        ArtifactGroupsWithArtifactsFetcher(
            googleMavenService = get(),
            dispatcherProvider = get()
        )
    }

    single {
        ArtifactGroupsWithArtifactsPersister(
            transactionRunner = get(),
            dao = get()
        )
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

    single {
        DefaultArtifactCollections()
    }

    single<ArtifactCollectionRepository> {
        ArtifactCollectionRepositoryImpl(
            databaseTransactionRunner = get(),
            artifactCollectionDao = get(),
            defaultArtifactCollections = get()
        )
    }

    single<ArtifactRepository> {
        ArtifactRepositoryImpl(
            artifactGroupsWithArtifactsStore = get()
        )
    }
}
