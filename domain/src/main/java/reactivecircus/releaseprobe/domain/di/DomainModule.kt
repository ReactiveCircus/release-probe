package reactivecircus.releaseprobe.domain.di

import org.koin.dsl.module
import reactivecircus.releaseprobe.domain.artifact.interactor.FetchArtifactGroups
import reactivecircus.releaseprobe.domain.artifact.interactor.GetArtifactGroups
import reactivecircus.releaseprobe.domain.artifactcollection.interactor.GetOrCreateArtifactCollections

val domainModule = module {

    single {
        FetchArtifactGroups(
            artifactRepository = get(),
            schedulerProvider = get()
        )
    }

    single {
        GetArtifactGroups(
            artifactRepository = get(),
            schedulerProvider = get()
        )
    }

    single {
        GetOrCreateArtifactCollections(
            artifactCollectionRepository = get(),
            schedulerProvider = get()
        )
    }
}
