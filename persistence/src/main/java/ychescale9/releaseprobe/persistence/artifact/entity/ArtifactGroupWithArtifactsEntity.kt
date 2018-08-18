package ychescale9.releaseprobe.persistence.artifact.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ArtifactGroupWithArtifactsEntity(
    @Embedded
    var artifactGroup: ArtifactGroupEntity? = null,
    @Relation(
            parentColumn = "group_id",
            entityColumn = "group_id",
            entity = ArtifactEntity::class
    )
    var artifacts: List<ArtifactEntity> = ArrayList()
)
