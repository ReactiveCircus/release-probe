package ychescale9.releaseprobe.persistence.artifact.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Flowable
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactEntity

@Dao
abstract class ArtifactDao {

    @Transaction
    @Query("SELECT * FROM artifact WHERE group_id = :groupId")
    abstract fun artifactsByGroupId(groupId: String): Flowable<List<ArtifactEntity>>
}
