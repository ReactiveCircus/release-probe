package ychescale9.releaseprobe.domain.di

import org.koin.dsl.module.module
import ychescale9.releaseprobe.domain.artifact.usecase.FetchArtifactGroups
import ychescale9.releaseprobe.domain.artifact.usecase.GetArtifactGroups
import ychescale9.releaseprobe.domain.artifactcollection.usecase.GetOrCreateArtifactCollections

val domainModule = module {

    single<FetchArtifactGroups>()

    single<GetArtifactGroups>()

    single<GetOrCreateArtifactCollections>()
}
