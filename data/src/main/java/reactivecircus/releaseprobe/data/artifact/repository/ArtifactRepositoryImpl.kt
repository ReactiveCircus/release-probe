package reactivecircus.releaseprobe.data.artifact.repository

import com.nytimes.android.external.store3.base.impl.BarCode
import com.nytimes.android.external.store3.base.impl.room.StoreRoom
import io.reactivex.BackpressureStrategy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import reactivecircus.releaseprobe.domain.artifact.model.ArtifactGroup
import reactivecircus.releaseprobe.domain.artifact.repository.ArtifactRepository

class ArtifactRepositoryImpl(
    private val artifactGroupsWithArtifactsStore: StoreRoom<List<ArtifactGroup>, BarCode>
) : ArtifactRepository {

    @ExperimentalCoroutinesApi
    override fun streamAllArtifactGroups(): Flow<List<ArtifactGroup>> {
        return artifactGroupsWithArtifactsStore.get(BarCode.empty())
            .toFlowable(BackpressureStrategy.LATEST)
            .asFlow()
    }

    @ExperimentalCoroutinesApi
    override fun fetchAllArtifactGroups(): Flow<List<ArtifactGroup>> {
        return artifactGroupsWithArtifactsStore.fetch(BarCode.empty())
            .toFlowable(BackpressureStrategy.LATEST)
            .asFlow()
    }
}
