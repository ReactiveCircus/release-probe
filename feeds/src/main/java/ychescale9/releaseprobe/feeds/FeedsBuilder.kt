package ychescale9.releaseprobe.feeds

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FeedsBuilder {

    @ContributesAndroidInjector()
    abstract fun bindFeedsFragment(): FeedsFragment
}
