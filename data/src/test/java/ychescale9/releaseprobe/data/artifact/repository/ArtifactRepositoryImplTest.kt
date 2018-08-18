package ychescale9.releaseprobe.data.artifact.repository

import com.nytimes.android.external.store3.base.impl.BarCode
import com.nytimes.android.external.store3.base.impl.room.StoreRoom
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Test
import ychescale9.releaseprobe.domain.artifact.model.Artifact
import ychescale9.releaseprobe.domain.artifact.model.ArtifactGroup

class ArtifactRepositoryImplTest {

    private val dummyArtifactGroups = listOf(
            ArtifactGroup("androidx.core", listOf(
                    Artifact("androidx.core", "core", listOf("2.0")),
                    Artifact("androidx.core", "core-ktx", listOf("2.0"))
            )),
            ArtifactGroup("androidx.test", listOf(
                    Artifact("androidx.test", "runner", listOf("1.0")),
                    Artifact("androidx.test", "rules", listOf("1.0"))
            ))
    )

    private val artifactGroupsWithArtifactsStore = mockk<StoreRoom<List<ArtifactGroup>, BarCode>> {
        every { get(BarCode.empty()) } returns Observable.just(dummyArtifactGroups)
        every { fetch(BarCode.empty()) } returns Observable.just(dummyArtifactGroups)
    }

    private val artifactRepository = ArtifactRepositoryImpl(artifactGroupsWithArtifactsStore)

    @Test
    fun `should get all artifact groups from store when subscribed`() {
        val testObserver = artifactRepository.getAllArtifactGroups().test()
        verify(exactly = 1) { artifactGroupsWithArtifactsStore.get(BarCode.empty()) }
        testObserver.assertValue(dummyArtifactGroups)
    }

    @Test
    fun `should fetch artifact groups from store when subscribed`() {
        val testObserver = artifactRepository.fetchAllArtifactGroups().test()
        verify(exactly = 1) { artifactGroupsWithArtifactsStore.fetch(BarCode.empty()) }
        testObserver.assertValue(dummyArtifactGroups)
    }
}
