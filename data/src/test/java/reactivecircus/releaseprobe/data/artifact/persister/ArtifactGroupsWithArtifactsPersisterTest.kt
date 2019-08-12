package reactivecircus.releaseprobe.data.artifact.persister

import com.nytimes.android.external.store3.base.impl.BarCode
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import reactivecircus.releaseprobe.data.testutil.TestTransactionRunner
import reactivecircus.releaseprobe.domain.artifact.model.Artifact
import reactivecircus.releaseprobe.domain.artifact.model.ArtifactGroup
import reactivecircus.releaseprobe.persistence.artifact.dao.ArtifactGroupDao
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactEntity
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactGroupEntity
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactGroupWithArtifactsEntity

@ExperimentalCoroutinesApi
class ArtifactGroupsWithArtifactsPersisterTest {

    private val dummyArtifactGroupsWithArtifactsEntities = listOf(
        ArtifactGroupWithArtifactsEntity(
            ArtifactGroupEntity("androidx.core"), listOf(
                ArtifactEntity("androidx.core", "core", listOf("2.0")),
                ArtifactEntity("androidx.core", "core-ktx", listOf("2.0"))
            )
        ),
        ArtifactGroupWithArtifactsEntity(
            ArtifactGroupEntity("androidx.test"), listOf(
                ArtifactEntity("androidx.test", "runner", listOf("1.0")),
                ArtifactEntity("androidx.test", "rules", listOf("1.0"))
            )
        )
    )

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

    private val dao = mockk<ArtifactGroupDao>(relaxUnitFun = true) {
        every { allArtifactGroupsWithArtifacts() } returns flowOf(
            dummyArtifactGroupsWithArtifactsEntities
        )
    }

    private val persister = ArtifactGroupsWithArtifactsPersister(TestTransactionRunner, dao)

    @Test
    fun `read from persister`() {
        val testObserver = persister.read(BarCode.empty()).test()

        verify(exactly = 1) {
            dao.allArtifactGroupsWithArtifacts()
        }
        testObserver.assertValue(dummyArtifactGroups)
    }

    @Test
    fun `write to persister`() {
        persister.write(BarCode.empty(), dummyArtifactGroupsWithArtifactsEntities)
        coVerify(exactly = 1) {
            dao.insertArtifactGroupsWithArtifacts(dummyArtifactGroupsWithArtifactsEntities)
        }
    }
}
