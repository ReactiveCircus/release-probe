package ychescale9.releaseprobe.data.artifact.persister

import com.nytimes.android.external.store3.base.impl.BarCode
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Flowable
import org.junit.Test
import ychescale9.releaseprobe.domain.artifact.model.Artifact
import ychescale9.releaseprobe.domain.artifact.model.ArtifactGroup
import ychescale9.releaseprobe.persistence.artifact.dao.ArtifactGroupDao
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactEntity
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactGroupEntity
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactGroupWithArtifactsEntity

class ArtifactGroupsWithArtifactsPersisterTest {

    private val dummyArtifactGroupsWithArtifactsEntities = listOf(
            ArtifactGroupWithArtifactsEntity(
                    ArtifactGroupEntity("androidx.core"), listOf(
                    ArtifactEntity("androidx.core", "core", listOf("2.0")),
                    ArtifactEntity("androidx.core", "core-ktx", listOf("2.0"))
            )),
            ArtifactGroupWithArtifactsEntity(
                    ArtifactGroupEntity("androidx.test"), listOf(
                    ArtifactEntity("androidx.test", "runner", listOf("1.0")),
                    ArtifactEntity("androidx.test", "rules", listOf("1.0"))
            ))
    )

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

    private val dao = mockk<ArtifactGroupDao>(relaxUnitFun = true) {
        every { allArtifactGroupsWithArtifacts() } returns Flowable.just(dummyArtifactGroupsWithArtifactsEntities)
    }

    private val persister = ArtifactGroupsWithArtifactsPersister(dao)

    @Test
    fun `should read`() {
        val testObserver = persister.read(BarCode.empty()).test()

        verify(exactly = 1) {
            dao.allArtifactGroupsWithArtifacts()
        }
        testObserver.assertValue(dummyArtifactGroups)
    }

    @Test
    fun `should write`() {
        persister.write(BarCode.empty(), dummyArtifactGroupsWithArtifactsEntities)
        verify(exactly = 1) {
            dao.insertArtifactGroupsWithArtifacts(dummyArtifactGroupsWithArtifactsEntities)
        }
    }
}
