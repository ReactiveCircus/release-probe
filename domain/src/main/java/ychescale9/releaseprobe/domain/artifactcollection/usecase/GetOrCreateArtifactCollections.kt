package ychescale9.releaseprobe.domain.artifactcollection.usecase

import io.reactivex.Observable
import ychescale9.infra.SchedulerProvider
import ychescale9.infra.domain.EmptyParams
import ychescale9.infra.domain.ObservableUseCase
import ychescale9.releaseprobe.domain.artifactcollection.model.ArtifactCollection
import ychescale9.releaseprobe.domain.artifactcollection.repository.ArtifactCollectionRepository

class GetOrCreateArtifactCollections(
    private val artifactCollectionRepository: ArtifactCollectionRepository,
    schedulerProvider: SchedulerProvider
) : ObservableUseCase<EmptyParams, List<ArtifactCollection>>(schedulerProvider = schedulerProvider) {

    override fun createUseCase(): Observable<List<ArtifactCollection>> {
        return artifactCollectionRepository.getArtifactCollections()
            .flatMap { collections ->
                if (collections.isEmpty()) {
                    artifactCollectionRepository
                        .insertDefaultArtifactCollections()
                        .toObservable()
                        .flatMap {
                            artifactCollectionRepository.getArtifactCollections()
                        }
                } else {
                    Observable.just(collections)
                }
            }
    }
}
