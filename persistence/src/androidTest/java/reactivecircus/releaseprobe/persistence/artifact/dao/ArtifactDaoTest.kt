package reactivecircus.releaseprobe.persistence.artifact.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.MediumTest
import org.junit.Rule
import org.junit.Test
import reactivecircus.releaseprobe.persistence.BaseDaoTest
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactEntity
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactGroupEntity
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactGroupWithArtifactsEntity

@MediumTest
class ArtifactDaoTest : BaseDaoTest() {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var artifactGroupDao: ArtifactGroupDao
    private lateinit var artifactDao: ArtifactDao

    private val artifactGroup1 = ArtifactGroupEntity("androidx.arch.core")
    private val artifactGroup2 = ArtifactGroupEntity("androidx.test")

    private val artifactA = ArtifactEntity(
            "androidx.arch.core",
            "core-common",
            listOf("2.0.0-alpha1", "2.0.0-beta01", "2.0.0-rc01")
    )
    private val artifactB = ArtifactEntity(
            "androidx.arch.core",
            "core-runtime",
            listOf("2.0.0-alpha1", "2.0.0-beta01", "2.0.0-rc01")
    )
    private val artifactC = ArtifactEntity(
            "androidx.test",
            "core",
            listOf("1.0.0-alpha2", "1.0.0-alpha3", "1.0.0-alpha4")
    )
    private val artifactD = ArtifactEntity(
            "androidx.test",
            "runner",
            listOf("1.1.0-alpha1", "1.1.0-alpha2", "1.1.0-alpha3", "1.1.0-alpha4")
    )

    private val artifactGroupWithArtifacts1 = ArtifactGroupWithArtifactsEntity(artifactGroup1, listOf(
            artifactA,
            artifactB
    ))
    private val artifactGroupWithArtifacts2 = ArtifactGroupWithArtifactsEntity(artifactGroup2, listOf(
            artifactC,
            artifactD
    ))

    override fun setUp() {
        super.setUp()
        artifactGroupDao = database.artifactGroupDao()
        artifactDao = database.artifactDao()
    }

    @Test
    fun artifactsByGroupId() {
        artifactGroupDao.insertArtifactGroupsWithArtifacts(listOf(
                artifactGroupWithArtifacts1,
                artifactGroupWithArtifacts2
        ))

        artifactDao.artifactsByGroupId(artifactGroup1.groupId).test().assertValue { artifacts ->
            // order doesn't matter
            artifacts.containsAll(listOf(artifactA, artifactB))
        }

        artifactDao.artifactsByGroupId(artifactGroup2.groupId).test().assertValue { artifacts ->
            // order doesn't matter
            artifacts.containsAll(listOf(artifactC, artifactD))
        }
    }

    @Test
    fun artifactsByGroupId_notFound() {
        artifactGroupDao.insertArtifactGroupsWithArtifacts(listOf(
                artifactGroupWithArtifacts1
        ))

        artifactDao.artifactsByGroupId(artifactGroup2.groupId).test().assertValue(
                emptyList()
        )
    }
}
