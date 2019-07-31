package reactivecircus.releaseprobe.domain.di

import org.koin.dsl.module
import reactivecircus.releaseprobe.domain.artifact.interactor.FetchArtifactGroups
import reactivecircus.releaseprobe.domain.artifact.interactor.StreamArtifactGroups
import reactivecircus.releaseprobe.domain.artifactcollection.interactor.StreamArtifactCollections

val domainModule = module {

    single {
        FetchArtifactGroups(
            artifactRepository = get(),
            dispatcherProvider = get()
        )
    }

    single {
        StreamArtifactGroups(
            artifactRepository = get(),
            dispatcherProvider = get()
        )
    }

    single {
        StreamArtifactCollections(
            artifactCollectionRepository = get(),
            dispatcherProvider = get()
        )
    }
}
