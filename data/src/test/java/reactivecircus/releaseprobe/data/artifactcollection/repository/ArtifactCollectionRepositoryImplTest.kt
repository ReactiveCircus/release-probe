package reactivecircus.releaseprobe.data.artifactcollection.repository

import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Flowable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldEqual
import org.junit.Test
import reactivecircus.releaseprobe.data.artifactcollection.DefaultArtifactCollections
import reactivecircus.releaseprobe.data.testutil.TestTransactionRunner
import reactivecircus.releaseprobe.domain.artifactcollection.model.ArtifactCollection
import reactivecircus.releaseprobe.persistence.artifactcollection.dao.ArtifactCollectionDao
import reactivecircus.releaseprobe.persistence.artifactcollection.entity.ArtifactCollectionEntity

@ExperimentalCoroutinesApi
class ArtifactCollectionRepositoryImplTest {

    private val artifactCollectionEntities = listOf(
        ArtifactCollectionEntity(
            "AndroidX",
            "Android extension libraries - a repackage of the Android Support Library, following semantic versioning",
            "#60AF46",
            listOf("androidx", "com.google.android.material")
        ),
        ArtifactCollectionEntity(
            "Firebase",
            "Google's mobile platform for developing apps, improving app quality and growing business.",
            "#D55D09",
            listOf("com.google.firebase", "com.crashlytics.sdk.android")
        )
    )

    private val dummyArtifactCollections = listOf(
        ArtifactCollection(
            "AndroidX",
            "Android extension libraries - a repackage of the Android Support Library, following semantic versioning",
            "#60AF46",
            listOf("androidx", "com.google.android.material")
        ),
        ArtifactCollection(
            "Firebase",
            "Google's mobile platform for developing apps, improving app quality and growing business.",
            "#D55D09",
            listOf("com.google.firebase", "com.crashlytics.sdk.android")
        )
    )

    private val artifactCollectionDao = mockk<ArtifactCollectionDao>(relaxUnitFun = true) {
        every { allArtifactCollections() } returns Flowable.just(artifactCollectionEntities)
    }

    private val defaultArtifactCollections = DefaultArtifactCollections()

    private val artifactCollectionRepository = ArtifactCollectionRepositoryImpl(
        TestTransactionRunner,
        artifactCollectionDao,
        defaultArtifactCollections
    )

    @Test
    fun `should stream artifact collections from dao`() = runBlockingTest {
        val result = artifactCollectionRepository.streamArtifactCollections().single()
        verify(exactly = 1) {
            artifactCollectionDao.allArtifactCollections()
        }
        result shouldEqual dummyArtifactCollections
    }

    @Test
    fun `should get artifact collections from dao`() = runBlockingTest {
        val result = artifactCollectionRepository.getArtifactCollections()
        verify(exactly = 1) {
            artifactCollectionDao.allArtifactCollections()
        }
        result shouldEqual dummyArtifactCollections
    }

    @Test
    fun `should insert default artifact collections to dao when subscribed`() = runBlockingTest {
        artifactCollectionRepository.insertDefaultArtifactCollections()
        coVerify(exactly = 1) {
            artifactCollectionDao.insertArtifactCollections(defaultArtifactCollections.get())
        }
    }
}
