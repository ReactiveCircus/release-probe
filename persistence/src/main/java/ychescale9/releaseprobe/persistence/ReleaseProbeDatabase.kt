package ychescale9.releaseprobe.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import ychescale9.releaseprobe.persistence.dao.ArtifactGroupDao
import ychescale9.releaseprobe.persistence.entity.ArtifactEntity
import ychescale9.releaseprobe.persistence.entity.ArtifactGroupEntity
import ychescale9.releaseprobe.persistence.entity.VersionEntity

@Database(
        entities = [
            ArtifactGroupEntity::class,
            ArtifactEntity::class,
            VersionEntity::class
        ],
        version = BuildConfig.DATABASE_SCHEMA_VERSION
)
abstract class ReleaseProbeDatabase : RoomDatabase() {

    abstract fun artifactGroupDao(): ArtifactGroupDao
}
