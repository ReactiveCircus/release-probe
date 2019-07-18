package reactivecircus.releaseprobe.persistence.artifact.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "artifact_group",
        indices = [
            Index(value = ["group_id"], unique = true)
        ])
data class ArtifactGroupEntity(
    @PrimaryKey
    @ColumnInfo(name = "group_id") val groupId: String
)
