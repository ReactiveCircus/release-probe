package ychescale9.releaseprobe.testing.di.component

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ychescale9.releaseprobe.browsecollections.BrowseCollectionsBuilder
import ychescale9.releaseprobe.feeds.FeedsBuilder
import ychescale9.releaseprobe.settings.SettingsBuilder
import ychescale9.releaseprobe.testing.SingleFragmentActivity
import ychescale9.releaseprobe.watchlist.WatchlistBuilder

@Module
abstract class TestMainBuilder {

    @ContributesAndroidInjector(modules = [
        FeedsBuilder::class,
        WatchlistBuilder::class,
        BrowseCollectionsBuilder::class,
        SettingsBuilder::class
    ])
    abstract fun bindMainActivity(): SingleFragmentActivity
}
