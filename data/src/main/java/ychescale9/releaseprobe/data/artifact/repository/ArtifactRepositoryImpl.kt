package ychescale9.releaseprobe.data.artifact.repository

import com.nytimes.android.external.store3.base.impl.BarCode
import com.nytimes.android.external.store3.base.impl.room.StoreRoom
import io.reactivex.Observable
import ychescale9.releaseprobe.domain.artifact.model.ArtifactGroup
import ychescale9.releaseprobe.domain.artifact.repository.ArtifactRepository

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
