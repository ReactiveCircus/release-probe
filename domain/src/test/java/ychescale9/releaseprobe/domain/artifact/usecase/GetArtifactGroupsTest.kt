package ychescale9.releaseprobe.domain.artifact.usecase

import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import ychescale9.releaseprobe.domain.artifact.model.ArtifactGroup
import ychescale9.releaseprobe.domain.artifact.repository.ArtifactRepository
import ychescale9.releaseprobe.domain.testutil.TestSchedulerProvider

class GetArtifactGroupsTest {

    private val artifactGroups = listOf<ArtifactGroup>()

    private val artifactRepository = mockk<ArtifactRepository> {
        every { getAllArtifactGroups() } returns Observable.just(artifactGroups)
    }

    val getArtifactGroups = GetArtifactGroups(
            artifactRepository,
            TestSchedulerProvider()
    )

    // TODO test filtering by keywords, versions order etc
}
