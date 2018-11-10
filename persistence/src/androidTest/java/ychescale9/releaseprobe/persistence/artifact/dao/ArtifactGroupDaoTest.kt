package ychescale9.releaseprobe.persistence.artifact.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.MediumTest
import org.junit.Rule
import org.junit.Test
import ychescale9.releaseprobe.persistence.BaseDaoTest
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactEntity
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactGroupEntity
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactGroupWithArtifactsEntity

@MediumTest
class ArtifactGroupDaoTest : BaseDaoTest() {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: ArtifactGroupDao

    private val artifactGroup1 = ArtifactGroupEntity("androidx.arch.core")
    private val artifactGroup2 = ArtifactGroupEntity("androidx.test")
    private val artifactGroup3 = ArtifactGroupEntity("com.google.firebase")

    private val artifactA = ArtifactEntity(
            "androidx.arch.core",
            "core-common",
            listOf("2.0.0-alpha1", "2.0.0-beta01", "2.0.0-rc01")
    )
    private val artifactA2 = ArtifactEntity(
            "androidx.arch.core",
            "core-common",
            listOf("2.0.0-alpha1", "2.0.0-beta01", "2.0.0-rc01", "2.0.0")
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
    private val artifactE = ArtifactEntity(
            "com.google.firebase",
            "firebase-analytics",
            listOf("12.0.0", "15.0.0", "16.0.0", "16.0.1")
    )

    private val artifactGroupWithArtifacts1 = ArtifactGroupWithArtifactsEntity(artifactGroup1, listOf(
            artifactA,
            artifactB
    ))
    private val artifactGroupWithArtifacts2 = ArtifactGroupWithArtifactsEntity(artifactGroup2, listOf(
            artifactC,
            artifactD
    ))
    private val artifactGroupWithArtifacts3 = ArtifactGroupWithArtifactsEntity(artifactGroup3, listOf(
            artifactE
    ))

    override fun setUp() {
        super.setUp()
        dao = database.artifactGroupDao()
    }

    @Test
    fun insertArtifactGroups_newItems() {
        // first artifact group and its artifacts
        dao.insertArtifactGroups(listOf(artifactGroup1))
        dao.insertArtifacts(listOf(artifactA, artifactB))

        dao.allArtifactGroupsWithArtifacts().test().assertValue(listOf(artifactGroupWithArtifacts1))

        // more artifact groups and their artifacts
        dao.insertArtifactGroups(listOf(artifactGroup2, artifactGroup3))
        dao.insertArtifacts(listOf(artifactC, artifactD, artifactE))
        dao.allArtifactGroupsWithArtifacts().test().assertValue(listOf(
                artifactGroupWithArtifacts1,
                artifactGroupWithArtifacts2,
                artifactGroupWithArtifacts3
        ))
    }

    @Test
    fun insertArtifactGroups_existingItems() {
        // first artifact group and its artifacts
        dao.insertArtifactGroups(listOf(artifactGroup1))
        dao.insertArtifacts(listOf(artifactA, artifactB))

        dao.allArtifactGroupsWithArtifacts().test().assertValue(listOf(artifactGroupWithArtifacts1))

        // insert same artifact group again
        dao.insertArtifactGroups(listOf(artifactGroup1))
        dao.insertArtifacts(listOf(artifactA, artifactB))
        dao.allArtifactGroupsWithArtifacts().test().assertValue(listOf(
                artifactGroupWithArtifacts1
        ))
    }

    @Test
    fun insertArtifactGroupsWithArtifacts_newItems() {
        dao.insertArtifactGroupsWithArtifacts(listOf(
                artifactGroupWithArtifacts1,
                artifactGroupWithArtifacts2,
                artifactGroupWithArtifacts3
        ))

        dao.allArtifactGroupsWithArtifacts().test().assertValue(listOf(
                artifactGroupWithArtifacts1,
                artifactGroupWithArtifacts2,
                artifactGroupWithArtifacts3
        ))
    }

    @Test
    fun insertArtifactGroupsWithArtifacts_existingItems() {
        // insert artifact groups with artifacts
        dao.insertArtifactGroupsWithArtifacts(listOf(
                artifactGroupWithArtifacts1,
                artifactGroupWithArtifacts2,
                artifactGroupWithArtifacts3
        ))

        // insert one of the existing artifact groups with NEW version of the same artifacts
        val newArtifactGroupWithArtifacts1 = ArtifactGroupWithArtifactsEntity(
                artifactGroup1, listOf(artifactA2, artifactB)
        )
        dao.insertArtifactGroupsWithArtifacts(listOf(newArtifactGroupWithArtifacts1))

        dao.allArtifactGroupsWithArtifacts().test().assertValue { artifacts ->
            // order doesn't matter
            artifacts.containsAll(listOf(
                    newArtifactGroupWithArtifacts1,
                    artifactGroupWithArtifacts2,
                    artifactGroupWithArtifacts3)
            )
        }
    }

    @Test
    fun artifactGroupById() {
        dao.insertArtifactGroups(listOf(artifactGroup1, artifactGroup2, artifactGroup3))

        dao.artifactGroupById(artifactGroup1.groupId).test().assertValue(artifactGroup1)
        dao.artifactGroupById(artifactGroup2.groupId).test().assertValue(artifactGroup2)
        dao.artifactGroupById(artifactGroup3.groupId).test().assertValue(artifactGroup3)
    }

    @Test
    fun artifactGroupById_notFound() {
        dao.insertArtifactGroups(listOf(artifactGroup1, artifactGroup2, artifactGroup3))
        dao.insertArtifacts(listOf(artifactA, artifactB, artifactC, artifactD, artifactE))

        dao.artifactGroupById("unknown.artifact.group").test()
                .assertComplete()
                .assertNoValues()
    }
}
