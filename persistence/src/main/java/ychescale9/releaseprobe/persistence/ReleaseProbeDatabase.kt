package ychescale9.releaseprobe.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ychescale9.releaseprobe.persistence.artifact.dao.ArtifactDao
import ychescale9.releaseprobe.persistence.artifact.dao.ArtifactGroupDao
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactEntity
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactGroupEntity

@Database(
        entities = [
            ArtifactGroupEntity::class,
            ArtifactEntity::class
        ],
        version = BuildConfig.DATABASE_SCHEMA_VERSION
)
@TypeConverters(Converters::class)
abstract class ReleaseProbeDatabase : RoomDatabase() {

    abstract fun artifactGroupDao(): ArtifactGroupDao

    abstract fun artifactDao(): ArtifactDao
}
