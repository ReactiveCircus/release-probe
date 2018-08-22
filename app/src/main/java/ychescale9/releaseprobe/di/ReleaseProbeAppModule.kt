package ychescale9.releaseprobe.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import ychescale9.releaseprobe.ReleaseProbeApp
import ychescale9.releaseprobe.util.AnimationHelper

@Module
object ReleaseProbeAppModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideApp(app: ReleaseProbeApp): Application = app

    @Provides
    @Singleton
    @JvmStatic
    fun provideContext(app: ReleaseProbeApp): Context = app.applicationContext

    @Provides
    @Singleton
    @JvmStatic
    fun provideAnimationHelper() = AnimationHelper()
}
