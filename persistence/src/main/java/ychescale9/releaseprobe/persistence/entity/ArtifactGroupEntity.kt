package ychescale9.releaseprobe.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "artifact_group",
        indices = [
            Index(value = ["name"], unique = true)
        ])
data class ArtifactGroupEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String
)
