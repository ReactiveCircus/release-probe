package ychescale9.releaseprobe.persistence.artifact.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ArtifactGroupWithArtifactsEntity(
    @Embedded
    val artifactGroup: ArtifactGroupEntity,
    @Relation(
            parentColumn = "group_id",
            entityColumn = "group_id",
            entity = ArtifactEntity::class
    )
    val artifacts: List<ArtifactEntity> = ArrayList()
)
