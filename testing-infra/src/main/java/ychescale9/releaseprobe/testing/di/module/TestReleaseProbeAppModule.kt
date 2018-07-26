package ychescale9.releaseprobe.testing.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import ychescale9.releaseprobe.testing.ScreenTestApp
import javax.inject.Singleton

@Module
class TestReleaseProbeAppModule {

    @Provides
    @Singleton
    fun provideApp(app: ScreenTestApp): Application = app

    @Provides
    @Singleton
    fun provideContext(app: ScreenTestApp): Context = app.applicationContext
}
