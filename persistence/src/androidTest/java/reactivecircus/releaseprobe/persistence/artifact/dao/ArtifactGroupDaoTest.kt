package reactivecircus.releaseprobe.persistence.artifact.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.MediumTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldContainAll
import org.amshove.kluent.shouldEqual
import org.junit.Rule
import org.junit.Test
import reactivecircus.releaseprobe.persistence.BaseDaoTest
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactEntity
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactGroupEntity
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactGroupWithArtifactsEntity

@ExperimentalCoroutinesApi
@MediumTest
class ArtifactGroupDaoTest : BaseDaoTest() {

    @get:Rule
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

    private val artifactGroupWithArtifacts1 = ArtifactGroupWithArtifactsEntity(
        artifactGroup1,
        listOf(
            artifactA,
            artifactB
        )
    )
    private val artifactGroupWithArtifacts2 = ArtifactGroupWithArtifactsEntity(
        artifactGroup2,
        listOf(
            artifactC,
            artifactD
        )
    )
    private val artifactGroupWithArtifacts3 = ArtifactGroupWithArtifactsEntity(
        artifactGroup3,
        listOf(
            artifactE
        )
    )

    override fun setUp() {
        super.setUp()
        dao = database.artifactGroupDao()
    }

    @Test
    fun insertArtifactGroups_newItems() = runBlockingTest {
        // first artifact group and its artifacts
        dao.insertArtifactGroups(listOf(artifactGroup1))
        dao.insertArtifacts(listOf(artifactA, artifactB))

        dao.allArtifactGroupsWithArtifacts().first() shouldEqual listOf(artifactGroupWithArtifacts1)

        // more artifact groups and their artifacts
        dao.insertArtifactGroups(listOf(artifactGroup2, artifactGroup3))
        dao.insertArtifacts(listOf(artifactC, artifactD, artifactE))
        dao.allArtifactGroupsWithArtifacts().first() shouldEqual listOf(
            artifactGroupWithArtifacts1,
            artifactGroupWithArtifacts2,
            artifactGroupWithArtifacts3
        )
    }

    @Test
    fun insertArtifactGroups_existingItems() = runBlockingTest {
        // first artifact group and its artifacts
        dao.insertArtifactGroups(listOf(artifactGroup1))
        dao.insertArtifacts(listOf(artifactA, artifactB))

        dao.allArtifactGroupsWithArtifacts().first() shouldEqual listOf(artifactGroupWithArtifacts1)

        // insert same artifact group again
        dao.insertArtifactGroups(listOf(artifactGroup1))
        dao.insertArtifacts(listOf(artifactA, artifactB))
        dao.allArtifactGroupsWithArtifacts().first() shouldEqual listOf(artifactGroupWithArtifacts1)
    }

    @Test
    fun insertArtifactGroupsWithArtifacts_newItems() = runBlockingTest {
        dao.insertArtifactGroupsWithArtifacts(
            listOf(
                artifactGroupWithArtifacts1,
                artifactGroupWithArtifacts2,
                artifactGroupWithArtifacts3
            )
        )

        dao.allArtifactGroupsWithArtifacts().first() shouldEqual listOf(
            artifactGroupWithArtifacts1,
            artifactGroupWithArtifacts2,
            artifactGroupWithArtifacts3
        )
    }

    @Test
    fun insertArtifactGroupsWithArtifacts_existingItems() = runBlockingTest {
        // insert artifact groups with artifacts
        dao.insertArtifactGroupsWithArtifacts(
            listOf(
                artifactGroupWithArtifacts1,
                artifactGroupWithArtifacts2,
                artifactGroupWithArtifacts3
            )
        )

        // insert one of the existing artifact groups with NEW version of the same artifacts
        val newArtifactGroupWithArtifacts1 = ArtifactGroupWithArtifactsEntity(
            artifactGroup1,
            listOf(artifactA2, artifactB)
        )
        dao.insertArtifactGroupsWithArtifacts(listOf(newArtifactGroupWithArtifacts1))

        val result = dao.allArtifactGroupsWithArtifacts().first()
        // order doesn't matter
        result shouldContainAll listOf(
            newArtifactGroupWithArtifacts1,
            artifactGroupWithArtifacts2,
            artifactGroupWithArtifacts3
        )
    }

    @Test
    fun artifactGroupById() = runBlockingTest {
        dao.insertArtifactGroups(listOf(artifactGroup1, artifactGroup2, artifactGroup3))

        dao.artifactGroupById(artifactGroup1.groupId) shouldEqual artifactGroup1
        dao.artifactGroupById(artifactGroup2.groupId) shouldEqual artifactGroup2
        dao.artifactGroupById(artifactGroup3.groupId) shouldEqual artifactGroup3
    }

    @Test
    fun artifactGroupById_notFound() = runBlockingTest {
        dao.insertArtifactGroups(listOf(artifactGroup1, artifactGroup2, artifactGroup3))
        dao.insertArtifacts(listOf(artifactA, artifactB, artifactC, artifactD, artifactE))

        dao.artifactGroupById("unknown.artifact.group") shouldEqual null
    }
}
