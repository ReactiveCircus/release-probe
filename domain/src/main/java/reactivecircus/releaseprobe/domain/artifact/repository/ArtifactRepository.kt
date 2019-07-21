package reactivecircus.releaseprobe.domain.artifact.repository

import kotlinx.coroutines.flow.Flow
import reactivecircus.releaseprobe.domain.artifact.model.ArtifactGroup

interface ArtifactRepository {

    fun streamAllArtifactGroups(): Flow<List<ArtifactGroup>>

    fun fetchAllArtifactGroups(): Flow<List<ArtifactGroup>>
}
