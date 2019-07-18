package reactivecircus.releaseprobe.browsecollections

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test
import reactivecircus.blueprint.interactor.EmptyParams
import reactivecircus.releaseprobe.domain.artifactcollection.model.ArtifactCollection
import reactivecircus.releaseprobe.domain.artifactcollection.interactor.GetOrCreateArtifactCollections

class ArtifactCollectionsViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dummyArtifactCollections = listOf<ArtifactCollection>(mockk(), mockk())

    private val getOrCreateArtifactCollections = mockk<GetOrCreateArtifactCollections> {
        every { buildObservable(any<EmptyParams>()) } returns Observable.just(dummyArtifactCollections)
    }

    private val artifactCollectionsObserver = mockk<Observer<List<ArtifactCollection>>>(relaxed = true)

    private val viewModel: ArtifactCollectionsViewModel by lazy {
        ArtifactCollectionsViewModel(getOrCreateArtifactCollections)
    }

    @Test
    fun `should emit Artifact Collections when initialized`() {
        viewModel.artifactCollectionsLiveData.observeForever(artifactCollectionsObserver)

        verify(exactly = 1) { getOrCreateArtifactCollections.buildObservable(any()) }
        verify(exactly = 1) {
            artifactCollectionsObserver.onChanged(
                    dummyArtifactCollections
            )
        }
    }
}
