package ychescale9.releaseprobe.data.artifact.mapper

import org.amshove.kluent.shouldEqual
import org.junit.Test
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactEntity
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactGroupEntity
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactGroupWithArtifactsEntity

class ArtifactGroupWithArtifactsEntityToModelTest {

    private val mapper = ArtifactGroupWithArtifactsEntityToModel()

    @Test
    fun `should transform ArtifactGroupWithArtifactsEntity to ArtifactGroup model`() {
        val artifactGroupEntity = ArtifactGroupEntity("androidx.test")
        val artifactEntities = listOf(
                ArtifactEntity(
                        artifactGroupEntity.groupId,
                        "core",
                        listOf("1.0", "2.0", "3.0")
                ),
                ArtifactEntity(
                        artifactGroupEntity.groupId,
                        "rules",
                        listOf("1.0", "2.0", "3.0", "3.1")
                ),
                ArtifactEntity(
                        artifactGroupEntity.groupId,
                        "runner",
                        listOf("1.0", "2.0", "3.0", "3.1-RC")
                )
        )

        val actual = mapper.transform(ArtifactGroupWithArtifactsEntity(artifactGroupEntity, artifactEntities))

        actual.run {
            groupId shouldEqual artifactGroupEntity.groupId
            artifacts.run {
                get(0).groupId shouldEqual artifactGroupEntity.groupId
                get(0).artifactId shouldEqual "core"
                get(0).versions shouldEqual listOf("1.0", "2.0", "3.0")
                get(1).groupId shouldEqual artifactGroupEntity.groupId
                get(1).artifactId shouldEqual "rules"
                get(1).versions shouldEqual listOf("1.0", "2.0", "3.0", "3.1")
                get(2).groupId shouldEqual artifactGroupEntity.groupId
                get(2).artifactId shouldEqual "runner"
                get(2).versions shouldEqual listOf("1.0", "2.0", "3.0", "3.1-RC")
            }
        }
    }
}
