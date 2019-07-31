package reactivecircus.releaseprobe.settings

import org.koin.core.module.Module
import reactivecircus.releaseprobe.di.settingsModule
import reactivecircus.releaseprobe.testing.BaseTestApp

class SettingsTestApp : BaseTestApp() {

    override fun loadUiModules(): List<Module> {
        return listOf(settingsModule)
    }
}
