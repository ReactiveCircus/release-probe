package ychescale9.releaseprobe.data.artifact.mapper

import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotEqual
import org.junit.Test
import ychescale9.releaseprobe.remote.artifact.dto.ArtifactDTO
import ychescale9.releaseprobe.remote.artifact.dto.ArtifactGroupDTO

class ArtifactGroupWithArtifactsDtosToEntityTest {

    private val mapper = ArtifactGroupWithArtifactsDtosToEntity()

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

        val actual = mapper.transform(Pair(artifactGroupDTO, artifactDTOs))

        actual.run {
            artifactGroup shouldNotEqual null
            artifactGroup?.groupId shouldEqual artifactGroupDTO.groupId
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
}
