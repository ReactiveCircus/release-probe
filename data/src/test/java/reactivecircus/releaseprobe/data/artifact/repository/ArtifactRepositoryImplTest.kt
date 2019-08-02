package reactivecircus.releaseprobe.data.artifact.repository

import com.nytimes.android.external.store3.base.impl.BarCode
import com.nytimes.android.external.store3.base.impl.room.StoreRoom
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldEqual
import org.junit.Test
import reactivecircus.releaseprobe.domain.artifact.model.Artifact
import reactivecircus.releaseprobe.domain.artifact.model.ArtifactGroup

@ExperimentalCoroutinesApi
class ArtifactRepositoryImplTest {

    private val testDispatcher = TestCoroutineDispatcher()

    private val dummyArtifactGroups = listOf(
        ArtifactGroup(
            "androidx.core", listOf(
                Artifact("androidx.core", "core", listOf("2.0")),
                Artifact("androidx.core", "core-ktx", listOf("2.0"))
            )
        ),
        ArtifactGroup(
            "androidx.test", listOf(
                Artifact("androidx.test", "runner", listOf("1.0")),
                Artifact("androidx.test", "rules", listOf("1.0"))
            )
        )
    )

    private val artifactGroupsWithArtifactsStore = mockk<StoreRoom<List<ArtifactGroup>, BarCode>> {
        every { get(BarCode.empty()) } returns Observable.just(dummyArtifactGroups)
        every { fetch(BarCode.empty()) } returns Observable.just(dummyArtifactGroups)
    }

    private val artifactRepository = ArtifactRepositoryImpl(artifactGroupsWithArtifactsStore)

    @Test
    fun `get all artifact groups from store`() = testDispatcher.runBlockingTest {
        val results = artifactRepository.streamAllArtifactGroups().single()
        verify(exactly = 1) { artifactGroupsWithArtifactsStore.get(BarCode.empty()) }
        results shouldEqual dummyArtifactGroups
    }

    @Test
    fun `fetch artifact groups from store`() = testDispatcher.runBlockingTest {
        val results = artifactRepository.fetchAllArtifactGroups().single()
        verify(exactly = 1) { artifactGroupsWithArtifactsStore.fetch(BarCode.empty()) }
        results shouldEqual dummyArtifactGroups
    }
}
