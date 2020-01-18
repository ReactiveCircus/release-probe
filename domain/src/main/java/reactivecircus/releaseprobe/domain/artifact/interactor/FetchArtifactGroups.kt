package reactivecircus.releaseprobe.domain.artifact.interactor

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import reactivecircus.blueprint.interactor.InteractorParams
import reactivecircus.blueprint.interactor.coroutines.FlowInteractor
import reactivecircus.blueprint.async.coroutines.CoroutineDispatcherProvider
import reactivecircus.releaseprobe.domain.artifact.model.ArtifactGroup
import reactivecircus.releaseprobe.domain.artifact.repository.ArtifactRepository

class FetchArtifactGroups(
    private val artifactRepository: ArtifactRepository,
    dispatcherProvider: CoroutineDispatcherProvider
) : FlowInteractor<FetchArtifactGroups.Params, List<ArtifactGroup>>() {
    override val dispatcher: CoroutineDispatcher = dispatcherProvider.io

    override fun createFlow(params: Params): Flow<List<ArtifactGroup>> {
        return artifactRepository.fetchAllArtifactGroups()
        // TODO filter results, reverse versions: List<String>
    }

    class Params(internal val filters: List<String>) : InteractorParams
}
