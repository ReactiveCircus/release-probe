package reactivecircus.releaseprobe.domain.artifact.model

data class ArtifactGroup(
    val groupId: String,
    val artifacts: List<Artifact>
)
