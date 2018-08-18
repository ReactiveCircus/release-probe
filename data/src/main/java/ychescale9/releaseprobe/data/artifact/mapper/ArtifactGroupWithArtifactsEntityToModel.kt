package ychescale9.releaseprobe.data.artifact.mapper

import javax.inject.Inject
import ychescale9.infra.mapper.BaseDataMapper
import ychescale9.releaseprobe.domain.artifact.model.Artifact
import ychescale9.releaseprobe.domain.artifact.model.ArtifactGroup
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactGroupWithArtifactsEntity

/**
 * Maps [ArtifactGroupWithArtifactsEntity] to an [ArtifactGroup]
 */
class ArtifactGroupWithArtifactsEntityToModel @Inject
constructor() : BaseDataMapper<ArtifactGroupWithArtifactsEntity, ArtifactGroup>() {

    override fun transform(model: ArtifactGroupWithArtifactsEntity, vararg params: Any): ArtifactGroup {
        val artifactGroupEntity = model.artifactGroup
        val artifactEntities = model.artifacts
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
}
