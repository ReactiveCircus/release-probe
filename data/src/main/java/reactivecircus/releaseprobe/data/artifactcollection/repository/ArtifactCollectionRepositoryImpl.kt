package reactivecircus.releaseprobe.data.artifactcollection.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import reactivecircus.releaseprobe.data.artifactcollection.DefaultArtifactCollections
import reactivecircus.releaseprobe.data.artifactcollection.mapper.toModel
import reactivecircus.releaseprobe.domain.artifactcollection.model.ArtifactCollection
import reactivecircus.releaseprobe.domain.artifactcollection.repository.ArtifactCollectionRepository
import reactivecircus.releaseprobe.persistence.DatabaseTransactionRunner
import reactivecircus.releaseprobe.persistence.artifactcollection.dao.ArtifactCollectionDao

class ArtifactCollectionRepositoryImpl(
    private val databaseTransactionRunner: DatabaseTransactionRunner,
    private val artifactCollectionDao: ArtifactCollectionDao,
    private val defaultArtifactCollections: DefaultArtifactCollections
) : ArtifactCollectionRepository {

    override fun streamArtifactCollections(): Flow<List<ArtifactCollection>> {
        return artifactCollectionDao.allArtifactCollections()
            .map { artifactCollections ->
                artifactCollections.map { artifactCollection ->
                    artifactCollection.toModel()
                }
            }
    }

    override suspend fun getArtifactCollections(): List<ArtifactCollection> {
        return artifactCollectionDao.allArtifactCollections()
            .first()
            .map { artifactCollection ->
                artifactCollection.toModel()
            }
    }

    override suspend fun insertDefaultArtifactCollections() {
        databaseTransactionRunner {
            artifactCollectionDao.insertArtifactCollections(defaultArtifactCollections.get())
        }
    }
}
