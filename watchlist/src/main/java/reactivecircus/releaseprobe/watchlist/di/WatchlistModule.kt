package reactivecircus.releaseprobe.watchlist.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import reactivecircus.releaseprobe.watchlist.WatchlistViewModel

val watchlistModule = module {

    viewModel {
        // TODO
        WatchlistViewModel()
    }
}
