package ychescale9.releaseprobe.data.artifactcollection.mapper

import org.amshove.kluent.shouldEqual
import org.junit.Test
import ychescale9.releaseprobe.persistence.artifactcollection.entity.ArtifactCollectionEntity

class ArtifactCollectionMappersTest {

    @Test
    fun `should transform ArtifactCollectionEntity to ArtifactCollection model`() {
        val artifactCollectionEntity = ArtifactCollectionEntity(
                "AndroidX",
                "Android extension libraries - a repackage of the Android Support Library, following semantic versioning",
                "#60AF46",
                listOf("androidx", "com.google.android.material")
        )

        val actual = artifactCollectionEntity.toModel()

        actual.run {
            name shouldEqual artifactCollectionEntity.name
            description shouldEqual artifactCollectionEntity.description
            themeColor shouldEqual artifactCollectionEntity.themeColor
            keywords shouldEqual artifactCollectionEntity.keywords
        }
    }
}
