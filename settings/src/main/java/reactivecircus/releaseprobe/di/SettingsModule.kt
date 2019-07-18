package reactivecircus.releaseprobe.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import reactivecircus.releaseprobe.settings.SettingsViewModel

val settingsModule = module {

    viewModel {
        // TODO
        SettingsViewModel()
    }
}
