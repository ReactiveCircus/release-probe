package reactivecircus.releaseprobe.persistence.artifact.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Flowable
import io.reactivex.Maybe
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactEntity
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactGroupEntity
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactGroupWithArtifactsEntity

@Dao
abstract class ArtifactGroupDao {

    @Transaction
    @Query("SELECT * FROM artifact_group")
    abstract fun allArtifactGroupsWithArtifacts(): Flowable<List<ArtifactGroupWithArtifactsEntity>>

    @Query("SELECT * FROM artifact_group WHERE group_id = :groupId")
    abstract fun artifactGroupById(groupId: String): Maybe<ArtifactGroupEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertArtifactGroups(artifactGroups: List<ArtifactGroupEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertArtifacts(artifacts: List<ArtifactEntity>)

    @Transaction
    open fun insertArtifactGroupsWithArtifacts(artifactGroupWithArtifacts: List<ArtifactGroupWithArtifactsEntity>) {
        artifactGroupWithArtifacts.map { it.artifactGroup }.run {
            insertArtifactGroups(this)
        }
        artifactGroupWithArtifacts.forEach {
            insertArtifacts(it.artifacts)
        }
    }
}
