package ychescale9.releaseprobe.data.artifactcollection.repository

import io.reactivex.Observable
import io.reactivex.Single
import ychescale9.releaseprobe.data.artifactcollection.DefaultArtifactCollections
import ychescale9.releaseprobe.data.artifactcollection.mapper.ArtifactCollectionEntityToModel
import ychescale9.releaseprobe.domain.artifactcollection.model.ArtifactCollection
import ychescale9.releaseprobe.domain.artifactcollection.repository.ArtifactCollectionRepository
import ychescale9.releaseprobe.persistence.artifactcollection.dao.ArtifactCollectionDao

class ArtifactCollectionRepositoryImpl(
    private val artifactCollectionDao: ArtifactCollectionDao,
    private val mapper: ArtifactCollectionEntityToModel,
    private val defaultArtifactCollections: DefaultArtifactCollections
) : ArtifactCollectionRepository {

    override fun getArtifactCollections(): Observable<List<ArtifactCollection>> {
        return artifactCollectionDao.allArtifactCollections()
                .map { mapper.transform(it) }
                .toObservable()
    }

    override fun insertDefaultArtifactCollections(): Single<Boolean> {
        return Single.fromCallable {
            artifactCollectionDao.insertArtifactCollections(defaultArtifactCollections.get())
            return@fromCallable true
        }
    }
}
