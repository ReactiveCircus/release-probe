package ychescale9.releaseprobe.settings

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SettingsBuilder {

    @ContributesAndroidInjector()
    abstract fun bindSettingsFragment(): SettingsFragment
}
