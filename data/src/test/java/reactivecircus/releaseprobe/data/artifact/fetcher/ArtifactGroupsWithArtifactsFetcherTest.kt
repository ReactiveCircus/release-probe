package reactivecircus.releaseprobe.data.artifact.fetcher

import com.nytimes.android.external.store3.base.impl.BarCode
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Test
import reactivecircus.blueprint.threading.coroutines.SchedulerProvider
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactEntity
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactGroupEntity
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactGroupWithArtifactsEntity
import reactivecircus.releaseprobe.remote.artifact.api.GoogleMavenService
import reactivecircus.releaseprobe.remote.artifact.dto.ArtifactDTO
import reactivecircus.releaseprobe.remote.artifact.dto.ArtifactGroupDTO
import java.util.concurrent.TimeUnit

class ArtifactGroupsWithArtifactsFetcherTest {

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

    private val testScheduler = TestScheduler()

    private val service = mockk<GoogleMavenService>()

    private val schedulerProvider = mockk<SchedulerProvider> {
        every { io } returns testScheduler
    }

    private val fetcher = ArtifactGroupsWithArtifactsFetcher(
        service,
        schedulerProvider
    )

    @Test
    fun `should fetch all artifact groups first, and then artifacts for each group concurrently, maintaining order`() {
        val fetchArtifactsDuration1 = 1L
        val fetchArtifactsDuration2 = 5L // bottleneck
        val fetchArtifactsDuration3 = 3L

        every { service.fetchArtifactGroups() } returns Single.just(
            listOf(
                artifactGroup1, artifactGroup2, artifactGroup3
            )
        )

        every {
            service.fetchArtifactsByGroupId(
                artifactGroup1.groupId.replace(
                    ".",
                    "/"
                )
            )
        } returns Single.just(
            listOf(
                artifactA, artifactB
            )
        ).delay(fetchArtifactsDuration1, TimeUnit.SECONDS, testScheduler)

        every {
            service.fetchArtifactsByGroupId(
                artifactGroup2.groupId.replace(
                    ".",
                    "/"
                )
            )
        } returns Single.just(
            listOf(
                artifactC, artifactD
            )
        ).delay(fetchArtifactsDuration2, TimeUnit.SECONDS, testScheduler)

        every {
            service.fetchArtifactsByGroupId(
                artifactGroup3.groupId.replace(
                    ".",
                    "/"
                )
            )
        } returns Single.just(
            listOf(
                artifactE
            )
        ).delay(fetchArtifactsDuration3, TimeUnit.SECONDS, testScheduler)

        val testObserver = fetcher.fetch(BarCode.empty()).test()

        // 1 second before the expected duration
        testScheduler.advanceTimeBy(fetchArtifactsDuration2 - 1, TimeUnit.SECONDS)
        testObserver.assertNotTerminated()

        // after the expected duration
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        verify(exactly = 1) { service.fetchArtifactGroups() }
        verify(exactly = 3) {
            service.fetchArtifactsByGroupId(any())
        }
        testObserver.assertComplete()
            .assertValue(
                listOf(
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
            )
    }

    @Test
    fun `should throw error when fetching all artifact groups fails`() {
        every { service.fetchArtifactGroups() } returns Single.error(Exception())

        val testObserver = fetcher.fetch(BarCode.empty()).test()

        verify(exactly = 1) { service.fetchArtifactGroups() }
        verify(exactly = 0) { service.fetchArtifactsByGroupId(any()) }
        testObserver.assertError(Exception::class.java)
    }
}
