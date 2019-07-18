package reactivecircus.releaseprobe.persistence.artifact.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(tableName = "artifact",
        indices = [
                Index(value = ["group_id"], unique = false),
                Index(value = ["artifact_id"], unique = false)
        ],
        primaryKeys = ["group_id", "artifact_id"],
        foreignKeys = [
                ForeignKey(entity = ArtifactGroupEntity::class,
                        parentColumns = arrayOf("group_id"),
                        childColumns = arrayOf("group_id"),
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE)
        ])
data class ArtifactEntity(
    @ColumnInfo(name = "group_id") val groupId: String,
    @ColumnInfo(name = "artifact_id") val artifactId: String,
    @ColumnInfo(name = "versions") val versions: List<String>
)
