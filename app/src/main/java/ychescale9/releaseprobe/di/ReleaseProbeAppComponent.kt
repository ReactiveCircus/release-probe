package ychescale9.releaseprobe.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import ychescale9.infra.di.module.AppModule
import ychescale9.releaseprobe.ReleaseProbeApp
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ReleaseProbeAppModule::class,
    MainBuilder::class,
    ThirdPartyApiModule::class])
interface ReleaseProbeAppComponent {

    fun inject(app: ReleaseProbeApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun app(application: ReleaseProbeApp): Builder
        fun build(): ReleaseProbeAppComponent
    }
}
