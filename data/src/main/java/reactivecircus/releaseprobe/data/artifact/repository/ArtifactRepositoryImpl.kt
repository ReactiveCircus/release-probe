package reactivecircus.releaseprobe.data.artifact.repository

import com.nytimes.android.external.store3.base.impl.BarCode
import com.nytimes.android.external.store3.base.impl.room.StoreRoom
import io.reactivex.Observable
import reactivecircus.releaseprobe.domain.artifact.model.ArtifactGroup
import reactivecircus.releaseprobe.domain.artifact.repository.ArtifactRepository

class ArtifactRepositoryImpl(
    private val artifactGroupsWithArtifactsStore: StoreRoom<List<ArtifactGroup>, BarCode>
) : ArtifactRepository {

    override fun getAllArtifactGroups(): Observable<List<ArtifactGroup>> {
        return artifactGroupsWithArtifactsStore.get(BarCode.empty())
    }

    override fun fetchAllArtifactGroups(): Observable<List<ArtifactGroup>> {
        return artifactGroupsWithArtifactsStore.fetch(BarCode.empty())
    }
}
