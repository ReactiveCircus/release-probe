package ychescale9.releaseprobe.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import ychescale9.releaseprobe.MainTestApp
import ychescale9.releaseprobe.di.MainBuilder
import ychescale9.releaseprobe.testing.di.component.ScreenTestAppComponent
import ychescale9.releaseprobe.testing.di.module.TestApiModule
import ychescale9.releaseprobe.testing.di.module.TestAppModule
import ychescale9.releaseprobe.testing.di.module.TestReleaseProbeAppModule
import ychescale9.releaseprobe.testing.di.module.TestThirdPartyApiModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    TestAppModule::class,
    TestReleaseProbeAppModule::class,
    MainBuilder::class,
    TestThirdPartyApiModule::class,
    TestApiModule::class])
interface MainTestAppComponent : ScreenTestAppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun testApp(app: MainTestApp): MainTestAppComponent.Builder
        fun build(): MainTestAppComponent
    }
}
