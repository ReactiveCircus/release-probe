package reactivecircus.releaseprobe.domain.artifactcollection.interactor

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import reactivecircus.blueprint.interactor.EmptyParams
import reactivecircus.blueprint.interactor.coroutines.FlowInteractor
import reactivecircus.blueprint.async.coroutines.CoroutineDispatcherProvider
import reactivecircus.releaseprobe.domain.artifactcollection.model.ArtifactCollection
import reactivecircus.releaseprobe.domain.artifactcollection.repository.ArtifactCollectionRepository

class StreamArtifactCollections(
    private val artifactCollectionRepository: ArtifactCollectionRepository,
    dispatcherProvider: CoroutineDispatcherProvider
) : FlowInteractor<EmptyParams, List<ArtifactCollection>>() {
    override val dispatcher: CoroutineDispatcher = dispatcherProvider.io

    @ExperimentalCoroutinesApi
    override fun createFlow(params: EmptyParams): Flow<List<ArtifactCollection>> {
        return flow {
            if (artifactCollectionRepository.getArtifactCollections().isEmpty()) {
                artifactCollectionRepository.insertDefaultArtifactCollections()
            }
            emitAll(artifactCollectionRepository.streamArtifactCollections())
        }
    }
}
