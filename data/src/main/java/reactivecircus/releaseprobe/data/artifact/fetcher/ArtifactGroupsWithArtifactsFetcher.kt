package reactivecircus.releaseprobe.data.artifact.fetcher

import com.nytimes.android.external.store3.base.Fetcher
import com.nytimes.android.external.store3.base.impl.BarCode
import io.reactivex.Single
import reactivecircus.blueprint.threading.coroutines.SchedulerProvider
import reactivecircus.releaseprobe.data.artifact.mapper.toEntity
import reactivecircus.releaseprobe.persistence.artifact.entity.ArtifactGroupWithArtifactsEntity
import reactivecircus.releaseprobe.remote.artifact.api.GoogleMavenService

class ArtifactGroupsWithArtifactsFetcher(
    private val googleMavenService: GoogleMavenService,
    private val schedulerProvider: SchedulerProvider
) : Fetcher<List<ArtifactGroupWithArtifactsEntity>, BarCode> {

    override fun fetch(key: BarCode): Single<List<ArtifactGroupWithArtifactsEntity>> {
        return googleMavenService.fetchArtifactGroups()
            .toObservable()
            .flatMapIterable { it }
            .concatMapEager { artifactGroup ->
                val path = artifactGroup.groupId.replace(".", "/")
                googleMavenService.fetchArtifactsByGroupId(path)
                    .subscribeOn(schedulerProvider.io)
                    .map { artifacts ->
                        Pair(artifactGroup, artifacts).toEntity()
                    }
                    .toObservable()
            }
            .toList()
    }
}
