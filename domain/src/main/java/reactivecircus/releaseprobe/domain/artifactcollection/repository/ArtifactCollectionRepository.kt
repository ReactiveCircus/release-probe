package reactivecircus.releaseprobe.domain.artifactcollection.repository

import kotlinx.coroutines.flow.Flow
import reactivecircus.releaseprobe.domain.artifactcollection.model.ArtifactCollection

interface ArtifactCollectionRepository {

    fun streamArtifactCollections(): Flow<List<ArtifactCollection>>

    suspend fun getArtifactCollections(): List<ArtifactCollection>

    suspend fun insertDefaultArtifactCollections()
}
