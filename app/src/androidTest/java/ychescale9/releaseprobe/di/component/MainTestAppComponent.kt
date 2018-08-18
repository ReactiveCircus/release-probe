package ychescale9.releaseprobe.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import ychescale9.releaseprobe.data.di.DataModule
import ychescale9.releaseprobe.di.MainBuilder
import ychescale9.releaseprobe.persistence.di.PersistenceModule
import ychescale9.releaseprobe.remote.di.ApiModule
import ychescale9.releaseprobe.testing.ScreenTestApp
import ychescale9.releaseprobe.testing.di.component.ScreenTestAppComponent
import ychescale9.releaseprobe.testing.di.module.TestAppModule
import ychescale9.releaseprobe.testing.di.module.TestReleaseProbeAppModule
import ychescale9.releaseprobe.testing.di.module.TestThirdPartyApiModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    TestAppModule::class,
    TestReleaseProbeAppModule::class,
    TestThirdPartyApiModule::class,
    ApiModule::class,
    PersistenceModule::class,
    DataModule::class,
    MainBuilder::class])
interface MainTestAppComponent : ScreenTestAppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun testApp(app: ScreenTestApp): MainTestAppComponent.Builder
        fun build(): MainTestAppComponent
    }
}
