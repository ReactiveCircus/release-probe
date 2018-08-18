package ychescale9.releaseprobe.domain.artifact.repository

import io.reactivex.Observable
import ychescale9.releaseprobe.domain.artifact.model.ArtifactGroup

interface ArtifactRepository {

    fun getAllArtifactGroups(): Observable<List<ArtifactGroup>>

    fun fetchAllArtifactGroups(): Observable<List<ArtifactGroup>>
}
