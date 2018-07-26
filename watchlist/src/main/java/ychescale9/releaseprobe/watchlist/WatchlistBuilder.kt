package ychescale9.releaseprobe.watchlist

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WatchlistBuilder {

    @ContributesAndroidInjector()
    abstract fun bindWatchlistFragment(): WatchlistFragment

}
