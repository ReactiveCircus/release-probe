package reactivecircus.releaseprobe.domain.artifactcollection.interactor

import io.reactivex.Observable
import reactivecircus.blueprint.interactor.EmptyParams
import reactivecircus.blueprint.interactor.rx2.ObservableInteractor
import reactivecircus.blueprint.threading.coroutines.SchedulerProvider
import reactivecircus.releaseprobe.domain.artifactcollection.model.ArtifactCollection
import reactivecircus.releaseprobe.domain.artifactcollection.repository.ArtifactCollectionRepository

class GetOrCreateArtifactCollections(
    private val artifactCollectionRepository: ArtifactCollectionRepository,
    schedulerProvider: SchedulerProvider
) : ObservableInteractor<EmptyParams, List<ArtifactCollection>>(
    ioScheduler = schedulerProvider.io,
    uiScheduler = schedulerProvider.ui
) {

    override fun createInteractor(): Observable<List<ArtifactCollection>> {
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
