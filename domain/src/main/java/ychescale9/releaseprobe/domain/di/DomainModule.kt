package ychescale9.releaseprobe.domain.di

import org.koin.dsl.module
import ychescale9.releaseprobe.domain.artifact.usecase.FetchArtifactGroups
import ychescale9.releaseprobe.domain.artifact.usecase.GetArtifactGroups
import ychescale9.releaseprobe.domain.artifactcollection.usecase.GetOrCreateArtifactCollections

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
