package reactivecircus.releaseprobe.data.artifactcollection.mapper

import reactivecircus.releaseprobe.domain.artifactcollection.model.ArtifactCollection
import reactivecircus.releaseprobe.persistence.artifactcollection.entity.ArtifactCollectionEntity

fun ArtifactCollectionEntity.toModel(): ArtifactCollection =
    ArtifactCollection(
        name = name,
        description = description,
        themeColor = themeColor,
        keywords = keywords
    )
