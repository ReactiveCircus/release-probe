package reactivecircus.releaseprobe.persistence.artifactcollection.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.MediumTest
import org.junit.Rule
import org.junit.Test
import reactivecircus.releaseprobe.persistence.BaseDaoTest
import reactivecircus.releaseprobe.persistence.artifactcollection.entity.ArtifactCollectionEntity

@MediumTest
class ArtifactCollectionDaoTest : BaseDaoTest() {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: ArtifactCollectionDao

    private val artifactCollectionEntity1 = ArtifactCollectionEntity(
            "AndroidX",
            "Android extension libraries - a repackage of the Android Support Library, following semantic versioning",
            "#60AF46",
            listOf("androidx", "com.google.android.material")
    )

    private val artifactCollectionEntity1B = ArtifactCollectionEntity(
            "AndroidX",
            "New description",
            "#60AF46",
            listOf("androidx", "com.google.android.material")
    )

    private val artifactCollectionEntity2 = ArtifactCollectionEntity(
            "Firebase",
            "Google's mobile platform for developing apps, improving app quality and growing business.",
            "#D55D09",
            listOf("com.google.firebase", "com.crashlytics.sdk.android")
    )

    override fun setUp() {
        super.setUp()
        dao = database.artifactCollectionDao()
    }

    @Test
    fun insertArtifactCollections_newItems() {
        dao.allArtifactCollections().test().assertValue(emptyList())

        dao.insertArtifactCollections(listOf(artifactCollectionEntity1, artifactCollectionEntity2))

        dao.allArtifactCollections().test().assertValue(listOf(artifactCollectionEntity1, artifactCollectionEntity2))
    }

    @Test
    fun insertArtifactCollections_existingItems() {
        dao.insertArtifactCollections(listOf(artifactCollectionEntity1, artifactCollectionEntity2))

        // insert same collection again
        dao.insertArtifactCollections(listOf(artifactCollectionEntity1B))

        // same collection should NOT be inserted or updated
        dao.allArtifactCollections().test().assertValue(listOf(artifactCollectionEntity1, artifactCollectionEntity2))
    }
}
