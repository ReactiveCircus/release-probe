package reactivecircus.releaseprobe.domain.artifact.interactor

import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import reactivecircus.releaseprobe.domain.artifact.model.ArtifactGroup
import reactivecircus.releaseprobe.domain.artifact.repository.ArtifactRepository
import reactivecircus.releaseprobe.domain.testutil.testSchedulerProvider

class FetchArtifactGroupsTest {

    private val artifactGroups = listOf<ArtifactGroup>()

    private val artifactRepository = mockk<ArtifactRepository> {
        every { fetchAllArtifactGroups() } returns Observable.just(artifactGroups)
    }

    val fetchArtifactGroups = FetchArtifactGroups(
        artifactRepository,
        testSchedulerProvider
    )

    // TODO test filtering by keywords, versions order etc
}
