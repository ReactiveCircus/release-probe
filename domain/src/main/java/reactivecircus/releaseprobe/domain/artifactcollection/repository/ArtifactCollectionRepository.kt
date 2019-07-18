package reactivecircus.releaseprobe.domain.artifactcollection.repository

import io.reactivex.Observable
import io.reactivex.Single
import reactivecircus.releaseprobe.domain.artifactcollection.model.ArtifactCollection

interface ArtifactCollectionRepository {

    fun getArtifactCollections(): Observable<List<ArtifactCollection>>

    fun insertDefaultArtifactCollections(): Single<Boolean>
}
