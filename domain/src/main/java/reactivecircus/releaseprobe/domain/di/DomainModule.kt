package reactivecircus.releaseprobe.domain.di

import org.koin.dsl.module
import reactivecircus.releaseprobe.domain.artifact.interactor.FetchArtifactGroups
import reactivecircus.releaseprobe.domain.artifact.interactor.StreamArtifactGroups
import reactivecircus.releaseprobe.domain.artifactcollection.interactor.StreamArtifactCollections

val domainModule = module {

    single {
        FetchArtifactGroups(
            artifactRepository = get(),
            coroutineDispatchers = get()
        )
    }

    single {
        StreamArtifactGroups(
            artifactRepository = get(),
            coroutineDispatchers = get()
        )
    }

    single {
        StreamArtifactCollections(
            artifactCollectionRepository = get(),
            coroutineDispatchers = get()
        )
    }
}
