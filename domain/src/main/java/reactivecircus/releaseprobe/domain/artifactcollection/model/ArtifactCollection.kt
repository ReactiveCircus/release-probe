package reactivecircus.releaseprobe.domain.artifactcollection.model

data class ArtifactCollection(
    val name: String,
    val description: String,
    val themeColor: String,
    val keywords: List<String>
)
