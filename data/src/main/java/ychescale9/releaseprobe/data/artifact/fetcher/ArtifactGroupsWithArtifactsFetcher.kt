package ychescale9.releaseprobe.data.artifact.fetcher

import com.nytimes.android.external.store3.base.Fetcher
import com.nytimes.android.external.store3.base.impl.BarCode
import io.reactivex.Single
import ychescale9.infra.SchedulerProvider
import ychescale9.releaseprobe.data.artifact.mapper.ArtifactGroupWithArtifactsDtosToEntity
import ychescale9.releaseprobe.persistence.artifact.entity.ArtifactGroupWithArtifactsEntity
import ychescale9.releaseprobe.remote.artifact.api.GoogleMavenService

class ArtifactGroupsWithArtifactsFetcher(
    private val googleMavenService: GoogleMavenService,
    private val mapper: ArtifactGroupWithArtifactsDtosToEntity,
    private val schedulerProvider: SchedulerProvider
) : Fetcher<List<ArtifactGroupWithArtifactsEntity>, BarCode> {

    override fun fetch(key: BarCode): Single<List<ArtifactGroupWithArtifactsEntity>> {
        return googleMavenService.fetchArtifactGroups()
                .toObservable()
                .flatMapIterable { it }
                .concatMapEager { artifactGroup ->
                    val path = artifactGroup.groupId.replace(".", "/")
                    googleMavenService.fetchArtifactsByGroupId(path)
                            .subscribeOn(schedulerProvider.io())
                            .map { artifacts ->
                                mapper.transform(Pair(artifactGroup, artifacts))
                            }
                            .toObservable()
                }
                .toList()
    }
}
