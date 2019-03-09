package ychescale9.releaseprobe.data.artifact.mapper

import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotEqual
import org.junit.Test
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactEntity
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactGroupEntity
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactGroupWithArtifactsEntity
import ychescale9.releaseprobe.remote.artifact.dto.ArtifactDTO
import ychescale9.releaseprobe.remote.artifact.dto.ArtifactGroupDTO

class ArtifactGroupWithArtifactsMappersTest {

    @Test
    fun `should transform pair of ArtifactGroupDTO and list of ArtifactDTOs to ArtifactGroupWithArtifactsEntity`() {
        val artifactGroupDTO = ArtifactGroupDTO("androidx.test")
        val artifactDTOs = listOf(
                ArtifactDTO(
                        artifactGroupDTO.groupId,
                        "core",
                        "1.0, 2.0, 3.0"
                ),
                ArtifactDTO(
                        artifactGroupDTO.groupId,
                        "rules",
                        "1.0, 2.0, 3.0, 3.1"
                ),
                ArtifactDTO(
                        artifactGroupDTO.groupId,
                        "runner",
                        "1.0, 2.0, 3.0, 3.1-RC"
                )
        )

        val actual = Pair(artifactGroupDTO, artifactDTOs).toEntity()

        actual.run {
            artifactGroup shouldNotEqual null
            artifactGroup.groupId shouldEqual artifactGroupDTO.groupId
            artifacts.run {
                get(0).groupId shouldEqual artifactGroupDTO.groupId
                get(0).artifactId shouldEqual "core"
                get(0).versions shouldEqual listOf("1.0", "2.0", "3.0")
                get(1).groupId shouldEqual artifactGroupDTO.groupId
                get(1).artifactId shouldEqual "rules"
                get(1).versions shouldEqual listOf("1.0", "2.0", "3.0", "3.1")
                get(2).groupId shouldEqual artifactGroupDTO.groupId
                get(2).artifactId shouldEqual "runner"
                get(2).versions shouldEqual listOf("1.0", "2.0", "3.0", "3.1-RC")
            }
        }
    }

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

        val actual = ArtifactGroupWithArtifactsEntity(artifactGroupEntity, artifactEntities).toModel()

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
