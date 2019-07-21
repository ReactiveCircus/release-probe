package reactivecircus.releaseprobe.data.artifact.mapper

import reactivecircus.releaseprobe.domain.artifact.model.Artifact
import reactivecircus.releaseprobe.domain.artifact.model.ArtifactGroup
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactEntity
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactGroupEntity
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactGroupWithArtifactsEntity
import reactivecircus.releaseprobe.remote.artifact.dto.ArtifactDTO
import reactivecircus.releaseprobe.remote.artifact.dto.ArtifactGroupDTO

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
        artifactGroupEntity.groupId,
        artifactEntities.map { artifactEntity ->
            Artifact(
                artifactEntity.groupId,
                artifactEntity.artifactId,
                artifactEntity.versions
            )
        }
    )
}
