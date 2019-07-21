package reactivecircus.releaseprobe.persistence.artifact.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Flowable
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactEntity
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactGroupEntity
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactGroupWithArtifactsEntity

@Dao
abstract class ArtifactGroupDao {

    @Transaction
    @Query("SELECT * FROM artifact_group")
    abstract fun allArtifactGroupsWithArtifacts(): Flowable<List<ArtifactGroupWithArtifactsEntity>>

    @Query("SELECT * FROM artifact_group WHERE group_id = :groupId")
    abstract suspend fun artifactGroupById(groupId: String): ArtifactGroupEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertArtifactGroups(artifactGroups: List<ArtifactGroupEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertArtifacts(artifacts: List<ArtifactEntity>)

    open suspend fun insertArtifactGroupsWithArtifacts(
        artifactGroupWithArtifacts: List<ArtifactGroupWithArtifactsEntity>
    ) {
        artifactGroupWithArtifacts.map { it.artifactGroup }.run {
            insertArtifactGroups(this)
        }
        artifactGroupWithArtifacts.forEach {
            insertArtifacts(it.artifacts)
        }
    }
}
