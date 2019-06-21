package ychescale9.releaseprobe.feeds.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ychescale9.releaseprobe.feeds.FeedsViewModel

val feedsModule = module {

    viewModel {
        // TODO
        FeedsViewModel()
    }
}
