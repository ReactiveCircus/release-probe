package reactivecircus.releaseprobe.browsecollections

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import reactivecircus.coroutines.testing.CoroutinesTestRule
import reactivecircus.releaseprobe.domain.artifactcollection.interactor.StreamArtifactCollections
import reactivecircus.releaseprobe.domain.artifactcollection.model.ArtifactCollection

@ExperimentalCoroutinesApi
class BrowseCollectionsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val dummyArtifactCollections = listOf<ArtifactCollection>(mockk(), mockk())

    private val streamArtifactCollections = mockk<StreamArtifactCollections> {
        every { buildFlow(any()) } returns flowOf(dummyArtifactCollections)
    }

    private val artifactCollectionsObserver =
        mockk<Observer<List<ArtifactCollection>>>(relaxed = true)

    private val viewModel: BrowseCollectionsViewModel by lazy {
        BrowseCollectionsViewModel(streamArtifactCollections)
    }

    @Test
    fun `emit Artifact Collections when initialized`() = runBlockingTest {
        viewModel.artifactCollectionsLiveData.observeForever(artifactCollectionsObserver)

        verify(exactly = 1) { streamArtifactCollections.buildFlow(any()) }
        verify(exactly = 1) {
            artifactCollectionsObserver.onChanged(
                dummyArtifactCollections
            )
        }
    }
}
