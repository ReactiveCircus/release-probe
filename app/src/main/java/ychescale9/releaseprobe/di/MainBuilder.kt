package ychescale9.releaseprobe.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ychescale9.releaseprobe.browse.BrowseBuilder
import ychescale9.releaseprobe.feeds.FeedsBuilder
import ychescale9.releaseprobe.main.MainActivity
import ychescale9.releaseprobe.settings.SettingsBuilder
import ychescale9.releaseprobe.watchlist.WatchlistBuilder

@Module
abstract class MainBuilder {

    @ContributesAndroidInjector(modules = [
        FeedsBuilder::class,
        WatchlistBuilder::class,
        BrowseBuilder::class,
        SettingsBuilder::class
    ])
    abstract fun bindMainActivity(): MainActivity
}
