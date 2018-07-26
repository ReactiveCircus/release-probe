package ychescale9.releaseprobe.testing.di.module

import dagger.Module
import dagger.Provides
import retrofit2.mock.NetworkBehavior

import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object TestApiModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideNetworkBehavior(): NetworkBehavior {
        return NetworkBehavior.create().apply {
            // make sure behavior is deterministic
            setVariancePercent(0)
            // no delay by default
            setDelay(0, TimeUnit.MILLISECONDS)
            // no failure by default
            setFailurePercent(0)
        }
    }
}