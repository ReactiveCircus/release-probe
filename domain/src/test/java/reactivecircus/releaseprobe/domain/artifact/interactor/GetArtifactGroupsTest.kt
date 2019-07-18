package reactivecircus.releaseprobe.domain.artifact.interactor

import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import reactivecircus.releaseprobe.domain.artifact.model.ArtifactGroup
import reactivecircus.releaseprobe.domain.artifact.repository.ArtifactRepository
import reactivecircus.releaseprobe.domain.testutil.testSchedulerProvider

class GetArtifactGroupsTest {

    private val artifactGroups = listOf<ArtifactGroup>()

    private val artifactRepository = mockk<ArtifactRepository> {
        every { getAllArtifactGroups() } returns Observable.just(artifactGroups)
    }

    val getArtifactGroups = GetArtifactGroups(
        artifactRepository,
        testSchedulerProvider
    )

    // TODO test filtering by keywords, versions order etc
}
