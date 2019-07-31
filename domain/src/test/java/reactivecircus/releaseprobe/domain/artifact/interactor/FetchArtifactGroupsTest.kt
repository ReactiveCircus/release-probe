package reactivecircus.releaseprobe.domain.artifact.interactor

import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import reactivecircus.blueprint.threading.coroutines.CoroutineDispatchers
import reactivecircus.releaseprobe.domain.artifact.model.ArtifactGroup
import reactivecircus.releaseprobe.domain.artifact.repository.ArtifactRepository

@ExperimentalCoroutinesApi
class FetchArtifactGroupsTest {

    private val artifactGroups = listOf<ArtifactGroup>()

    private val artifactRepository = mockk<ArtifactRepository> {
        every { fetchAllArtifactGroups() } returns flowOf(artifactGroups)
    }

    private val coroutineDispatchers = mockk<CoroutineDispatchers> {
        every { io } returns TestCoroutineDispatcher()
    }

    val fetchArtifactGroups = FetchArtifactGroups(
        artifactRepository,
        coroutineDispatchers
    )

    // TODO test filtering by keywords, versions order etc
}