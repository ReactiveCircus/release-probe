package ychescale9.releaseprobe.persistence.artifactcollection.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Flowable
import ychescale9.releaseprobe.persistence.artifactcollection.entity.ArtifactCollectionEntity

@Dao
abstract class ArtifactCollectionDao {

    @Transaction
    @Query("SELECT * FROM artifact_collection")
    abstract fun allArtifactCollections(): Flowable<List<ArtifactCollectionEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertArtifactCollections(artifactCollections: List<ArtifactCollectionEntity>)
}
