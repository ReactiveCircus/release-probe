package reactivecircus.releaseprobe.data.artifact.persister

import com.nytimes.android.external.store3.base.impl.BarCode
import com.nytimes.android.external.store3.base.room.RoomPersister
import io.reactivex.Observable
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import reactivecircus.releaseprobe.data.artifact.mapper.toModel
import reactivecircus.releaseprobe.domain.artifact.model.ArtifactGroup
import reactivecircus.releaseprobe.persistence.DatabaseTransactionRunner
import reactivecircus.releaseprobe.persistence.artifact.dao.ArtifactGroupDao
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactGroupWithArtifactsEntity

class ArtifactGroupsWithArtifactsPersister(
    private val transactionRunner: DatabaseTransactionRunner,
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
        GlobalScope.launch {
            transactionRunner {
                dao.insertArtifactGroupsWithArtifacts(raw)
            }
        }
    }
}
