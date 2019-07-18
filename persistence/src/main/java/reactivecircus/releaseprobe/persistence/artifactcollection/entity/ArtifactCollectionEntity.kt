package reactivecircus.releaseprobe.persistence.artifactcollection.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "artifact_collection",
        indices = [
            Index(value = ["name"], unique = true)
        ])
data class ArtifactCollectionEntity(
    @PrimaryKey
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "theme_color") val themeColor: String,
    @ColumnInfo(name = "keywords") val keywords: List<String>
)
