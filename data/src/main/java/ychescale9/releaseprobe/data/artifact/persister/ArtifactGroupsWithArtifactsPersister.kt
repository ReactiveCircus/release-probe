package ychescale9.releaseprobe.data.artifact.persister

import com.nytimes.android.external.store3.base.impl.BarCode
import com.nytimes.android.external.store3.base.room.RoomPersister
import io.reactivex.Observable
import javax.inject.Inject
import ychescale9.releaseprobe.data.artifact.mapper.ArtifactGroupWithArtifactsEntityToModel
import ychescale9.releaseprobe.domain.artifact.model.ArtifactGroup
import ychescale9.releaseprobe.persistence.artifact.dao.ArtifactGroupDao
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactGroupWithArtifactsEntity

class ArtifactGroupsWithArtifactsPersister @Inject
constructor(
    private val dao: ArtifactGroupDao,
    private val mapper: ArtifactGroupWithArtifactsEntityToModel
) : RoomPersister<List<ArtifactGroupWithArtifactsEntity>, List<ArtifactGroup>, BarCode> {

    override fun read(key: BarCode): Observable<List<ArtifactGroup>> {
        return dao.allArtifactGroupsWithArtifacts()
                .map { mapper.transform(it) }
                .toObservable()
    }

    override fun write(key: BarCode, raw: List<ArtifactGroupWithArtifactsEntity>) {
        dao.insertArtifactGroupsWithArtifacts(raw)
    }
}
