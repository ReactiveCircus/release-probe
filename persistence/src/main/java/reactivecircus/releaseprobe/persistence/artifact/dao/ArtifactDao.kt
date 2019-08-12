package reactivecircus.releaseprobe.persistence.artifact.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactEntity

@Dao
abstract class ArtifactDao {

    @Transaction
    @Query("SELECT * FROM artifact WHERE group_id = :groupId")
    abstract fun artifactsByGroupId(groupId: String): Flow<List<ArtifactEntity>>
}
