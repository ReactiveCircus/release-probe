package ychescale9.releaseprobe

import android.content.Context
import io.mockk.mockk
import org.junit.Test
import org.koin.dsl.module.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.checkModules
import ychescale9.releaseprobe.di.modules

class KoinCheckModulesTest : AutoCloseKoinTest() {

    private val mockAndroid = module {
        single { mockk<Context>() }
    }

    @Test
    fun `check Koin configuration`() {
        checkModules(modules + mockAndroid)
    }
}
