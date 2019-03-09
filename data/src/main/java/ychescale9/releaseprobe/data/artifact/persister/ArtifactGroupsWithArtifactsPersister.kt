package ychescale9.releaseprobe.data.artifact.persister

import com.nytimes.android.external.store3.base.impl.BarCode
import com.nytimes.android.external.store3.base.room.RoomPersister
import io.reactivex.Observable
import ychescale9.releaseprobe.data.artifact.mapper.toModel
import ychescale9.releaseprobe.domain.artifact.model.ArtifactGroup
import ychescale9.releaseprobe.persistence.artifact.dao.ArtifactGroupDao
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactGroupWithArtifactsEntity

class ArtifactGroupsWithArtifactsPersister(
    private val dao: ArtifactGroupDao
) : RoomPersister<List<ArtifactGroupWithArtifactsEntity>, List<ArtifactGroup>, BarCode> {

    override fun read(key: BarCode): Observable<List<ArtifactGroup>> {
        return dao.allArtifactGroupsWithArtifacts()
            .map { items ->
                items.map { item ->
                    item.toModel()
                }
            }
            .toObservable()
    }

    override fun write(key: BarCode, raw: List<ArtifactGroupWithArtifactsEntity>) {
        dao.insertArtifactGroupsWithArtifacts(raw)
    }
}
