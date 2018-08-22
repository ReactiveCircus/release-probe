package ychescale9.releaseprobe.data.artifactcollection.mapper

import javax.inject.Inject
import ychescale9.infra.mapper.BaseDataMapper
import ychescale9.releaseprobe.domain.artifactcollection.model.ArtifactCollection
import ychescale9.releaseprobe.persistence.artifactcollection.entity.ArtifactCollectionEntity

/**
 * Maps [ArtifactCollectionEntity] to an [ArtifactCollection]
 */
class ArtifactCollectionEntityToModel @Inject
constructor() : BaseDataMapper<ArtifactCollectionEntity, ArtifactCollection>() {

    override fun transform(model: ArtifactCollectionEntity, vararg params: Any): ArtifactCollection {
        return ArtifactCollection(
                model.name,
                model.description,
                model.themeColor,
                model.keywords
        )
    }
}
