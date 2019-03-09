package ychescale9.releaseprobe.data.artifact.mapper

import ychescale9.releaseprobe.domain.artifact.model.Artifact
import ychescale9.releaseprobe.domain.artifact.model.ArtifactGroup
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactEntity
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactGroupEntity
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactGroupWithArtifactsEntity
import ychescale9.releaseprobe.remote.artifact.dto.ArtifactDTO
import ychescale9.releaseprobe.remote.artifact.dto.ArtifactGroupDTO

fun Pair<ArtifactGroupDTO, List<ArtifactDTO>>.toEntity(): ArtifactGroupWithArtifactsEntity {
    val artifactGroupDTO = first
    val artifactDTOs = second
    return ArtifactGroupWithArtifactsEntity(
        ArtifactGroupEntity(artifactGroupDTO.groupId),
        artifactDTOs.map { artifactDTO ->
            ArtifactEntity(
                artifactDTO.groupId,
                artifactDTO.artifactId,
                artifactDTO.versions.split(",").map { it.trim() }
            )
        }
    )
}

fun ArtifactGroupWithArtifactsEntity.toModel(): ArtifactGroup {
    val artifactGroupEntity = artifactGroup
    val artifactEntities = artifacts
    return ArtifactGroup(
        requireNotNull(artifactGroupEntity).groupId,
        artifactEntities.map { artifactEntity ->
            Artifact(
                artifactEntity.groupId,
                artifactEntity.artifactId,
                artifactEntity.versions
            )
        }
    )
}
