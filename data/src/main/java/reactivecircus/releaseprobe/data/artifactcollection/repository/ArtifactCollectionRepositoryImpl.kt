package reactivecircus.releaseprobe.data.artifactcollection.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.reactive.flow.asFlow
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

    @ExperimentalCoroutinesApi
    override fun streamArtifactCollections(): Flow<List<ArtifactCollection>> {
        return artifactCollectionDao.allArtifactCollections()
            .asFlow()
            .map { artifactCollections ->
                artifactCollections.map { artifactCollection ->
                    artifactCollection.toModel()
                }
            }
    }

    @ExperimentalCoroutinesApi
    override suspend fun getArtifactCollections(): List<ArtifactCollection> {
        return artifactCollectionDao.allArtifactCollections()
            .asFlow()
            .first()
            .map { artifactCollection ->
                artifactCollection.toModel()
            }
    }

    override suspend fun insertDefaultArtifactCollections() {
        GlobalScope.launch {
            databaseTransactionRunner {
                artifactCollectionDao.insertArtifactCollections(defaultArtifactCollections.get())
            }
        }
    }
}
