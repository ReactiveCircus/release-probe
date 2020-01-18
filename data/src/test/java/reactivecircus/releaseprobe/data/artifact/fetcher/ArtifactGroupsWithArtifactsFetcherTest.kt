package reactivecircus.releaseprobe.data.artifact.fetcher

import com.nytimes.android.external.store3.base.impl.BarCode
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.rx2.await
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.coInvoking
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldThrow
import org.junit.Test
import reactivecircus.blueprint.async.coroutines.CoroutineDispatcherProvider
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactEntity
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactGroupEntity
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactGroupWithArtifactsEntity
import reactivecircus.releaseprobe.remote.artifact.api.GoogleMavenService
import reactivecircus.releaseprobe.remote.artifact.dto.ArtifactDTO
import reactivecircus.releaseprobe.remote.artifact.dto.ArtifactGroupDTO
import reactivecircus.releaseprobe.remote.artifact.toPath
import java.io.IOException

@ExperimentalCoroutinesApi
class ArtifactGroupsWithArtifactsFetcherTest {

    private val testDispatcher = TestCoroutineDispatcher()

    private val artifactGroup1 = ArtifactGroupDTO("androidx.arch.core")
    private val artifactGroup2 = ArtifactGroupDTO("androidx.test")
    private val artifactGroup3 = ArtifactGroupDTO("com.google.firebase")

    private val artifactA = ArtifactDTO(
        "androidx.arch.core",
        "core-common",
        "2.0.0-alpha1, 2.0.0-beta01, 2.0.0-rc01"
    )
    private val artifactB = ArtifactDTO(
        "androidx.arch.core",
        "core-runtime",
        "2.0.0-alpha1, 2.0.0-beta01, 2.0.0-rc01"
    )
    private val artifactC = ArtifactDTO(
        "androidx.test",
        "core",
        "1.0.0-alpha2, 1.0.0-alpha3, 1.0.0-alpha4"
    )
    private val artifactD = ArtifactDTO(
        "androidx.test",
        "runner",
        "1.1.0-alpha1, 1.1.0-alpha2, 1.1.0-alpha3, 1.1.0-alpha4"
    )
    private val artifactE = ArtifactDTO(
        "com.google.firebase",
        "firebase-analytics",
        "12.0.0, 15.0.0, 16.0.0, 16.0.1"
    )

    private val service = mockk<GoogleMavenService>()

    private val dispatcherProvider = mockk<CoroutineDispatcherProvider> {
        every { io } returns testDispatcher
    }

    private val fetcher = ArtifactGroupsWithArtifactsFetcher(
        service,
        dispatcherProvider
    )

    @Test
    fun `fetch all artifact groups, followed by the artifacts for each group concurrently, maintaining order`() =
        testDispatcher.runBlockingTest {
            val fetchArtifactsDuration1 = 1000L
            val fetchArtifactsDuration2 = 5000L
            val fetchArtifactsDuration3 = 3000L

            // all artifact groups
            coEvery { service.fetchArtifactGroups() } returns listOf(
                artifactGroup1, artifactGroup2, artifactGroup3
            )

            // artifacts for artifact group 1
            coEvery {
                service.fetchArtifactsByGroupId(
                    artifactGroup1.groupId.toPath()
                )
            } coAnswers {
                delay(fetchArtifactsDuration1)
                listOf(artifactA, artifactB)
            }

            // artifacts for artifact group 2
            coEvery {
                service.fetchArtifactsByGroupId(
                    artifactGroup2.groupId.toPath()
                )
            } coAnswers {
                delay(fetchArtifactsDuration2)
                listOf(artifactC, artifactD)
            }

            // artifacts for artifact group 3
            coEvery {
                service.fetchArtifactsByGroupId(
                    artifactGroup3.groupId.toPath()
                )
            } coAnswers {
                delay(fetchArtifactsDuration3)
                listOf(artifactE)
            }

            val startTime = currentTime

            val result = fetcher.fetch(BarCode.empty()).await()

            coVerify(exactly = 1) { service.fetchArtifactGroups() }
            coVerify(exactly = 3) {
                service.fetchArtifactsByGroupId(any())
            }

            result shouldEqual listOf(
                ArtifactGroupWithArtifactsEntity(
                    ArtifactGroupEntity("androidx.arch.core"), listOf(
                        ArtifactEntity(
                            "androidx.arch.core",
                            "core-common",
                            listOf("2.0.0-alpha1", "2.0.0-beta01", "2.0.0-rc01")
                        ),
                        ArtifactEntity(
                            "androidx.arch.core",
                            "core-runtime",
                            listOf("2.0.0-alpha1", "2.0.0-beta01", "2.0.0-rc01")
                        )
                    )
                ),
                ArtifactGroupWithArtifactsEntity(
                    ArtifactGroupEntity("androidx.test"), listOf(
                        ArtifactEntity(
                            "androidx.test",
                            "core",
                            listOf("1.0.0-alpha2", "1.0.0-alpha3", "1.0.0-alpha4")
                        ),
                        ArtifactEntity(
                            "androidx.test",
                            "runner",
                            listOf(
                                "1.1.0-alpha1",
                                "1.1.0-alpha2",
                                "1.1.0-alpha3",
                                "1.1.0-alpha4"
                            )
                        )
                    )
                ),
                ArtifactGroupWithArtifactsEntity(
                    ArtifactGroupEntity("com.google.firebase"), listOf(
                        ArtifactEntity(
                            "com.google.firebase",
                            "firebase-analytics",
                            listOf("12.0.0", "15.0.0", "16.0.0", "16.0.1")
                        )
                    )
                )
            )

            // 3 fetchArtifactsByGroupId calls should be executed concurrently,
            // expected total duration should be the longest call
            val expectedDuration = listOf(
                fetchArtifactsDuration1,
                fetchArtifactsDuration2,
                fetchArtifactsDuration3
            ).max()

            currentTime - startTime shouldEqual expectedDuration
        }

    @Test
    fun `throw exception when fetching all artifact groups fails`() =
        testDispatcher.runBlockingTest {
            coEvery { service.fetchArtifactGroups() } throws IOException()

            coInvoking {
                fetcher.fetch(BarCode.empty()).await()
            } shouldThrow IOException::class

            coVerify(exactly = 1) { service.fetchArtifactGroups() }
            coVerify(exactly = 0) { service.fetchArtifactsByGroupId(any()) }
        }

    @Test
    fun `throw exception when fetching any artifact fails`() =
        testDispatcher.runBlockingTest {
            // all artifact groups
            coEvery { service.fetchArtifactGroups() } returns listOf(
                artifactGroup1, artifactGroup2, artifactGroup3
            )

            // fetching artifacts for artifact group 1 is successful
            coEvery {
                service.fetchArtifactsByGroupId(
                    artifactGroup1.groupId.toPath()
                )
            } coAnswers {
                delay(1000L)
                listOf(artifactA, artifactB)
            }

            // fetching artifacts for artifact group 2 throws exception
            coEvery {
                service.fetchArtifactsByGroupId(
                    artifactGroup2.groupId.toPath()
                )
            } coAnswers {
                delay(1000L)
                throw IOException()
            }

            // fetching artifacts for artifact group 3 is successful
            coEvery {
                service.fetchArtifactsByGroupId(
                    artifactGroup3.groupId.toPath()
                )
            } coAnswers {
                delay(1000L)
                listOf(artifactE)
            }

            coInvoking {
                fetcher.fetch(BarCode.empty()).await()
            } shouldThrow IOException::class

            coVerify(exactly = 1) { service.fetchArtifactGroups() }
            coVerify(exactly = 3) { service.fetchArtifactsByGroupId(any()) }
        }
}
