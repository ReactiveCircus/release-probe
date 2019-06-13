package ychescale9.releaseprobe.domain.artifact.usecase

import io.reactivex.Observable
import ychescale9.infra.SchedulerProvider
import ychescale9.infra.domain.ObservableUseCase
import ychescale9.infra.domain.UseCaseParams
import ychescale9.releaseprobe.domain.artifact.model.ArtifactGroup
import ychescale9.releaseprobe.domain.artifact.repository.ArtifactRepository

class GetArtifactGroups(
    private val artifactRepository: ArtifactRepository,
    schedulerProvider: SchedulerProvider
) : ObservableUseCase<GetArtifactGroups.Params, List<ArtifactGroup>>(schedulerProvider = schedulerProvider) {

    override fun createUseCase(): Observable<List<ArtifactGroup>> {
        return artifactRepository.getAllArtifactGroups()
        // TODO filter results, reverse versions: List<String>
    }

    class Params(internal val filters: List<String>) : UseCaseParams
}
