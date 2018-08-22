package ychescale9.releaseprobe.domain.artifactcollection.repository

import io.reactivex.Observable
import io.reactivex.Single
import ychescale9.releaseprobe.domain.artifactcollection.model.ArtifactCollection

interface ArtifactCollectionRepository {

    fun getArtifactCollections(): Observable<List<ArtifactCollection>>

    fun insertDefaultArtifactCollections(): Single<Boolean>
}
