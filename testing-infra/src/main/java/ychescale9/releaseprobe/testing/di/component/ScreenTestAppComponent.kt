package ychescale9.releaseprobe.testing.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
import ychescale9.releaseprobe.data.di.DataModule
import ychescale9.releaseprobe.persistence.di.PersistenceModule
import ychescale9.releaseprobe.remote.di.ApiModule
import ychescale9.releaseprobe.testing.BaseScreenTest
import ychescale9.releaseprobe.testing.ScreenTestApp
import ychescale9.releaseprobe.testing.di.module.TestAppModule
import ychescale9.releaseprobe.testing.di.module.TestReleaseProbeAppModule
import ychescale9.releaseprobe.testing.di.module.TestThirdPartyApiModule

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    TestAppModule::class,
    TestReleaseProbeAppModule::class,
    TestThirdPartyApiModule::class,
    ApiModule::class,
    PersistenceModule::class,
    DataModule::class,
    TestMainBuilder::class])
interface ScreenTestAppComponent {

    fun inject(app: ScreenTestApp)

    fun inject(test: BaseScreenTest)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun testApp(app: ScreenTestApp): ScreenTestAppComponent.Builder
        fun build(): ScreenTestAppComponent
    }
}
