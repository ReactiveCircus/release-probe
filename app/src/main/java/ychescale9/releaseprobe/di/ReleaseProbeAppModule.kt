package ychescale9.releaseprobe.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import ychescale9.releaseprobe.ReleaseProbeApp
import javax.inject.Singleton

@Module
class ReleaseProbeAppModule {

    @Provides
    @Singleton
    fun provideApp(app: ReleaseProbeApp): Application = app

    @Provides
    @Singleton
    fun provideContext(app: ReleaseProbeApp): Context = app.applicationContext
}
