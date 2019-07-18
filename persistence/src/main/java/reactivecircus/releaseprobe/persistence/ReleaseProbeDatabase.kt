package reactivecircus.releaseprobe.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import reactivecircus.releaseprobe.persistence.artifact.dao.ArtifactDao
import reactivecircus.releaseprobe.persistence.artifact.dao.ArtifactGroupDao
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactEntity
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactGroupEntity
import reactivecircus.releaseprobe.persistence.artifactcollection.dao.ArtifactCollectionDao
import reactivecircus.releaseprobe.persistence.artifactcollection.entity.ArtifactCollectionEntity

@Database(
        entities = [
            ArtifactCollectionEntity::class,
            ArtifactGroupEntity::class,
            ArtifactEntity::class
        ],
        version = BuildConfig.DATABASE_SCHEMA_VERSION
)
@TypeConverters(Converters::class)
abstract class ReleaseProbeDatabase : RoomDatabase() {

    abstract fun artifactCollectionDao(): ArtifactCollectionDao

    abstract fun artifactGroupDao(): ArtifactGroupDao

    abstract fun artifactDao(): ArtifactDao
}
