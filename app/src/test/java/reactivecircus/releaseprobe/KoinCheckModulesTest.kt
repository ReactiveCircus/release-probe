package reactivecircus.releaseprobe

import android.content.Context
import io.mockk.mockk
import org.junit.Test
import org.koin.core.parameter.parametersOf
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.check.checkModules
import reactivecircus.analytics.AnalyticsApi
import reactivecircus.releaseprobe.artifactlist.ArtifactListViewModel
import reactivecircus.releaseprobe.di.modules

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
        }.checkModules {
            create<ArtifactListViewModel> {
                parametersOf("")
            }
        }
    }
}
