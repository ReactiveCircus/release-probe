package ychescale9.releaseprobe.di

import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module
import org.koin.experimental.builder.single
import ychescale9.infra.SchedulerProvider
import ychescale9.infra.rx.AndroidSchedulerProvider
import ychescale9.infra.util.Clock
import ychescale9.infra.util.RealClock
import ychescale9.releaseprobe.browsecollections.ArtifactCollectionsViewModel
import ychescale9.releaseprobe.data.di.dataModule
import ychescale9.releaseprobe.domain.di.domainModule
import ychescale9.releaseprobe.persistence.di.persistenceModule
import ychescale9.releaseprobe.remote.di.apiModule
import ychescale9.releaseprobe.util.AnimationHelper

val appModule = module {

    single<SchedulerProvider> { AndroidSchedulerProvider() }

    single<Clock> { RealClock() }

    single<AnimationHelper>()
}

val viewModelModules = listOf(
        module(createOnStart = true) {
            // TODO
        },

        module(createOnStart = true) {
            // TODO
        },

        module(createOnStart = true) {
            // TODO
        },

        module(createOnStart = true) {
            viewModel<ArtifactCollectionsViewModel>()
        },

        module(createOnStart = true) {
            // TODO
        }
)

val modules = viewModelModules + listOf(
        appModule,
        domainModule,
        dataModule,
        persistenceModule,
        apiModule,
        thirdPartyApiModule
)
