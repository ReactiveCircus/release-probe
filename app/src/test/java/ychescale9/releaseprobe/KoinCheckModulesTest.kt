package ychescale9.releaseprobe

import android.content.Context
import io.mockk.mockk
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.check.checkModules
import ychescale9.analytics.AnalyticsApi
import ychescale9.releaseprobe.di.modules

class KoinCheckModulesTest : AutoCloseKoinTest() {

    private val mocks = module(override = true) {
        single {
            mockk<Context>()
        }
        single<AnalyticsApi> { mockk() }
    }

    @Test
    fun `check Koin modules`() {
        koinApplication {
            modules(modules + mocks)
        }.checkModules()
    }
}
