package ychescale9.releaseprobe.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "version",
        indices = [
                Index(value = ["artifact_id"], unique = true),
                Index(value = ["value"], unique = false)
        ],
        foreignKeys = [
                ForeignKey(entity = ArtifactEntity::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("artifact_id"),
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE)
        ])
data class VersionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "artifact_id") val artifactId: Long,
    @ColumnInfo(name = "value") val value: String
)
