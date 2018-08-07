package ychescale9.releaseprobe.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
import ychescale9.infra.di.module.AppModule
import ychescale9.releaseprobe.ReleaseProbeApp
import ychescale9.releaseprobe.persistence.di.PersistenceModule
import ychescale9.releaseprobe.remote.di.ApiModule

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ReleaseProbeAppModule::class,
    ThirdPartyApiModule::class,
    ApiModule::class,
    PersistenceModule::class,
    MainBuilder::class])
interface ReleaseProbeAppComponent {

    fun inject(app: ReleaseProbeApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun app(application: ReleaseProbeApp): Builder
        fun build(): ReleaseProbeAppComponent
    }
}
