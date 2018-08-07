package ychescale9.releaseprobe.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Flowable
import io.reactivex.Maybe
import ychescale9.releaseprobe.persistence.entity.ArtifactGroupEntity

@Dao
abstract class ArtifactGroupDao {

    @Transaction
    @Query("SELECT * FROM artifact_group")
    abstract fun getAllArtifacts(): Flowable<List<ArtifactGroupEntity>>

    @Query("SELECT * FROM artifact_group WHERE name = :name")
    abstract fun getArtifactGroupByName(name: String): Maybe<ArtifactGroupEntity>

    // TODO implement queries with relations / custom projections e.g. list of artifact groups with artifacts and their latest version, an artifact with its artifactGroup name
}
