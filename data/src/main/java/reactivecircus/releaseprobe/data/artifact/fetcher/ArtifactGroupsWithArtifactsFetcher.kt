package reactivecircus.releaseprobe.data.artifact.fetcher

import com.nytimes.android.external.store3.base.Fetcher
import com.nytimes.android.external.store3.base.impl.BarCode
import io.reactivex.Single
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.rx2.rxSingle
import reactivecircus.blueprint.async.coroutines.CoroutineDispatcherProvider
import reactivecircus.releaseprobe.data.artifact.mapper.toEntity
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactGroupWithArtifactsEntity
import reactivecircus.releaseprobe.remote.artifact.api.GoogleMavenService
import reactivecircus.releaseprobe.remote.artifact.toPath

class ArtifactGroupsWithArtifactsFetcher(
    private val googleMavenService: GoogleMavenService,
    private val dispatcherProvider: CoroutineDispatcherProvider
) : Fetcher<List<ArtifactGroupWithArtifactsEntity>, BarCode> {

    override fun fetch(key: BarCode): Single<List<ArtifactGroupWithArtifactsEntity>> {
        return rxSingle(dispatcherProvider.io) {
            googleMavenService.fetchArtifactGroups()
                .map { artifactGroup ->
                    async(dispatcherProvider.io) {
                        val path = artifactGroup.groupId.toPath()
                        googleMavenService.fetchArtifactsByGroupId(path)
                            .let { artifacts ->
                                Pair(artifactGroup, artifacts).toEntity()
                            }
                    }
                }
                .awaitAll()
        }
    }
}
