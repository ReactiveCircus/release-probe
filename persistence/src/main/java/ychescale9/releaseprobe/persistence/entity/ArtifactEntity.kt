package ychescale9.releaseprobe.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "artifact",
        indices = [
                Index(value = ["artifact_group_id"], unique = true),
                Index(value = ["name"], unique = false)
        ],
        foreignKeys = [
                ForeignKey(entity = ArtifactGroupEntity::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("artifact_group_id"),
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE)
        ])
data class ArtifactEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "artifact_group_id") val artifactGroupId: Long,
    @ColumnInfo(name = "name") val name: String
)
