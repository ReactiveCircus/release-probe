package ychescale9.releaseprobe.watchlist.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ychescale9.releaseprobe.watchlist.WatchlistViewModel

val watchlistModule = module {

    viewModel {
        // TODO
        WatchlistViewModel()
    }
}
