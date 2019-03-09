package ychescale9.releaseprobe.data.artifactcollection.mapper

import ychescale9.releaseprobe.domain.artifactcollection.model.ArtifactCollection
import ychescale9.releaseprobe.persistence.artifactcollection.entity.ArtifactCollectionEntity

fun ArtifactCollectionEntity.toModel(): ArtifactCollection =
    ArtifactCollection(
        name = name,
        description = description,
        themeColor = themeColor,
        keywords = keywords
    )
