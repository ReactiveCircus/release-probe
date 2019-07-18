package reactivecircus.releaseprobe.domain.artifactcollection.interactor

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Test
import reactivecircus.blueprint.interactor.EmptyParams
import reactivecircus.releaseprobe.domain.artifactcollection.model.ArtifactCollection
import reactivecircus.releaseprobe.domain.artifactcollection.repository.ArtifactCollectionRepository
import reactivecircus.releaseprobe.domain.testutil.testSchedulerProvider

class GetOrCreateArtifactCollectionsTest {

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

    private val artifactCollectionRepository = mockk<ArtifactCollectionRepository> {
        every { insertDefaultArtifactCollections() } returns Single.just(true)
    }

    private val getOrCreateArtifactCollections = GetOrCreateArtifactCollections(
        artifactCollectionRepository,
        testSchedulerProvider
    )

    @Test
    fun `should get artifact collections when there are existing artifact collections`() {
        every { artifactCollectionRepository.getArtifactCollections() } returns Observable.just(
            dummyArtifactCollections
        )

        val testObserver = getOrCreateArtifactCollections.buildObservable(EmptyParams).test()
        testObserver.awaitTerminalEvent()

        verify(exactly = 1) { artifactCollectionRepository.getArtifactCollections() }
        verify(exactly = 0) { artifactCollectionRepository.insertDefaultArtifactCollections() }
        testObserver.assertValue(dummyArtifactCollections)
    }

    @Test
    fun `should create default and get created artifact collections when no artifact collections exist`() {
        every { artifactCollectionRepository.getArtifactCollections() } returnsMany listOf(
            Observable.just(emptyList()),
            Observable.just(dummyArtifactCollections)
        )

        val testObserver = getOrCreateArtifactCollections.buildObservable(EmptyParams).test()
        testObserver.awaitTerminalEvent()

        verify(exactly = 2) { artifactCollectionRepository.getArtifactCollections() }
        verify(exactly = 1) { artifactCollectionRepository.insertDefaultArtifactCollections() }
        testObserver.assertValue(dummyArtifactCollections)
    }
}
