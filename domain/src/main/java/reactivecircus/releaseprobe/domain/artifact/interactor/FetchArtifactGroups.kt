package reactivecircus.releaseprobe.domain.artifact.interactor

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import reactivecircus.blueprint.interactor.InteractorParams
import reactivecircus.blueprint.interactor.coroutines.FlowInteractor
import reactivecircus.blueprint.threading.coroutines.CoroutineDispatchers
import reactivecircus.releaseprobe.domain.artifact.model.ArtifactGroup
import reactivecircus.releaseprobe.domain.artifact.repository.ArtifactRepository

class FetchArtifactGroups(
    private val artifactRepository: ArtifactRepository,
    coroutineDispatchers: CoroutineDispatchers
) : FlowInteractor<FetchArtifactGroups.Params, List<ArtifactGroup>>() {
    override val dispatcher: CoroutineDispatcher = coroutineDispatchers.io

    override fun createFlow(params: Params): Flow<List<ArtifactGroup>> {
        return artifactRepository.fetchAllArtifactGroups()
        // TODO filter results, reverse versions: List<String>
    }

    class Params(internal val filters: List<String>) : InteractorParams
}
