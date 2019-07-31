package reactivecircus.releaseprobe.feeds.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import reactivecircus.releaseprobe.feeds.FeedsViewModel

val feedsModule = module {

    viewModel {
        // TODO
        FeedsViewModel()
    }
}
