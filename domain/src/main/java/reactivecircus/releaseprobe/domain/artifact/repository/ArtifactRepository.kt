package reactivecircus.releaseprobe.domain.artifact.repository

import io.reactivex.Observable
import reactivecircus.releaseprobe.domain.artifact.model.ArtifactGroup

interface ArtifactRepository {

    fun getAllArtifactGroups(): Observable<List<ArtifactGroup>>

    fun fetchAllArtifactGroups(): Observable<List<ArtifactGroup>>
}
