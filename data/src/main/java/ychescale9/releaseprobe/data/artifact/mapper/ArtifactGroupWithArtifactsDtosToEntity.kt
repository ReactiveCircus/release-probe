package ychescale9.releaseprobe.data.artifact.mapper

import ychescale9.infra.mapper.BaseDataMapper
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactEntity
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactGroupEntity
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactGroupWithArtifactsEntity
import ychescale9.releaseprobe.remote.artifact.dto.ArtifactDTO
import ychescale9.releaseprobe.remote.artifact.dto.ArtifactGroupDTO

/**
 * Maps pair of [ArtifactGroupDTO] of list of [ArtifactDTO] to an [ArtifactGroupWithArtifactsEntity]
 */
class ArtifactGroupWithArtifactsDtosToEntity :
        BaseDataMapper<Pair<ArtifactGroupDTO, List<ArtifactDTO>>, ArtifactGroupWithArtifactsEntity>() {

    override fun transform(
        model: Pair<ArtifactGroupDTO, List<ArtifactDTO>>,
        vararg params: Any
    ): ArtifactGroupWithArtifactsEntity {
        val artifactGroupDTO = model.first
        val artifactDTOs = model.second
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
}
