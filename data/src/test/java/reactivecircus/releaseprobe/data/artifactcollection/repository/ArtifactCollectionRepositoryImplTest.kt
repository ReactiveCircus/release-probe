package reactivecircus.releaseprobe.data.artifactcollection.repository

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Flowable
import org.junit.Test
import reactivecircus.releaseprobe.data.artifactcollection.DefaultArtifactCollections
import reactivecircus.releaseprobe.domain.artifactcollection.model.ArtifactCollection
import reactivecircus.releaseprobe.persistence.artifactcollection.dao.ArtifactCollectionDao
import reactivecircus.releaseprobe.persistence.artifactcollection.entity.ArtifactCollectionEntity

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
            artifactCollectionDao,
            defaultArtifactCollections
    )

    @Test
    fun `should get artifact collections from dao when subscribed`() {
        val testObserver = artifactCollectionRepository.getArtifactCollections().test()
        verify(exactly = 1) {
            artifactCollectionDao.allArtifactCollections()
        }
        testObserver.assertValue(dummyArtifactCollections)
    }

    @Test
    fun `should insert default artifact collections to dao when subscribed`() {
        val testObserver = artifactCollectionRepository.insertDefaultArtifactCollections().test()
        verify(exactly = 1) {
            artifactCollectionDao.insertArtifactCollections(defaultArtifactCollections.get())
        }
        testObserver.assertValue(true)
    }
}
