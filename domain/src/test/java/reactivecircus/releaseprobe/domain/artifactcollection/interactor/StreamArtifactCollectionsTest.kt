package reactivecircus.releaseprobe.domain.artifactcollection.interactor

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldEqual
import org.junit.Test
import reactivecircus.blueprint.interactor.EmptyParams
import reactivecircus.blueprint.threading.coroutines.CoroutineDispatcherProvider
import reactivecircus.releaseprobe.domain.artifactcollection.model.ArtifactCollection
import reactivecircus.releaseprobe.domain.artifactcollection.repository.ArtifactCollectionRepository

@ExperimentalCoroutinesApi
class StreamArtifactCollectionsTest {

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
        coEvery { insertDefaultArtifactCollections() } returns Unit
    }

    private val dispatcherProvider = mockk<CoroutineDispatcherProvider> {
        every { io } returns TestCoroutineDispatcher()
    }

    private val streamArtifactCollections = StreamArtifactCollections(
        artifactCollectionRepository,
        dispatcherProvider
    )

    @Test
    fun `stream artifact collections from repository when there are existing artifact collections`() =
        runBlockingTest {
            coEvery { artifactCollectionRepository.getArtifactCollections() } returns dummyArtifactCollections
            coEvery { artifactCollectionRepository.streamArtifactCollections() } returns flowOf(
                dummyArtifactCollections
            )

            val result = streamArtifactCollections.buildFlow(EmptyParams).single()

            coVerifySequence {
                artifactCollectionRepository.getArtifactCollections()
                artifactCollectionRepository.streamArtifactCollections()
            }
            coVerify(exactly = 0) { artifactCollectionRepository.insertDefaultArtifactCollections() }
            result shouldEqual dummyArtifactCollections
        }

    @Test
    fun `create default and stream artifact collections when no artifact collections exist`() =
        runBlockingTest {
            coEvery { artifactCollectionRepository.getArtifactCollections() } returns emptyList()
            coEvery { artifactCollectionRepository.streamArtifactCollections() } returns flowOf(
                dummyArtifactCollections
            )

            val result = streamArtifactCollections.buildFlow(EmptyParams).single()

            coVerifySequence {
                artifactCollectionRepository.getArtifactCollections()
                artifactCollectionRepository.insertDefaultArtifactCollections()
                artifactCollectionRepository.streamArtifactCollections()
            }
            result shouldEqual dummyArtifactCollections
        }
}
