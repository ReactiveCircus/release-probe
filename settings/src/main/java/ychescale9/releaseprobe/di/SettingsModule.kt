package ychescale9.releaseprobe.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ychescale9.releaseprobe.settings.SettingsViewModel

val settingsModule = module {

    viewModel {
        // TODO
        SettingsViewModel()
    }
}
