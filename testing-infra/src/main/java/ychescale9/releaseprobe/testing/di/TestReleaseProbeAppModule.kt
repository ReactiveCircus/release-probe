package ychescale9.releaseprobe.testing.di

import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module
import ychescale9.infra.SchedulerProvider
import ychescale9.infra.util.Clock
import ychescale9.infra.util.RealClock
import ychescale9.releaseprobe.browsecollections.ArtifactCollectionsViewModel
import ychescale9.releaseprobe.data.di.dataModule
import ychescale9.releaseprobe.domain.di.domainModule
import ychescale9.releaseprobe.persistence.di.persistenceModule
import ychescale9.releaseprobe.remote.di.apiModule
import ychescale9.releaseprobe.testing.helper.ScreenTestAnimationHelper
import ychescale9.releaseprobe.testing.helper.ScreenTestSchedulerProvider
import ychescale9.releaseprobe.util.AnimationHelper

val testAppModule = module {

    single<SchedulerProvider> { ScreenTestSchedulerProvider() }

    single<Clock> { RealClock() }

    single<AnimationHelper> { ScreenTestAnimationHelper() }
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

val testModules = viewModelModules + listOf(
        testAppModule,
        domainModule,
        dataModule,
        persistenceModule,
        apiModule,
        testThirdPartyApiModule
)
