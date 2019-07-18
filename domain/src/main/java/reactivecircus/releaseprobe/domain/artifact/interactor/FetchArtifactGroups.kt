package reactivecircus.releaseprobe.domain.artifact.interactor

import io.reactivex.Observable
import reactivecircus.blueprint.interactor.InteractorParams
import reactivecircus.blueprint.interactor.rx2.ObservableInteractor
import reactivecircus.blueprint.threading.coroutines.SchedulerProvider
import reactivecircus.releaseprobe.domain.artifact.model.ArtifactGroup
import reactivecircus.releaseprobe.domain.artifact.repository.ArtifactRepository

class FetchArtifactGroups(
    private val artifactRepository: ArtifactRepository,
    schedulerProvider: SchedulerProvider
) : ObservableInteractor<FetchArtifactGroups.Params, List<ArtifactGroup>>(
    ioScheduler = schedulerProvider.io,
    uiScheduler = schedulerProvider.ui
) {

    override fun createInteractor(): Observable<List<ArtifactGroup>> {
        return artifactRepository.fetchAllArtifactGroups()
        // TODO filter results, reverse versions: List<String>
    }

    class Params(internal val filters: List<String>) : InteractorParams
}
