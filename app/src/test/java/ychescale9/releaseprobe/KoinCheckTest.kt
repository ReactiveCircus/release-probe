package ychescale9.releaseprobe

import android.content.Context
import io.mockk.mockk
import org.junit.Test
import org.koin.dsl.module.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.check
import ychescale9.releaseprobe.di.modules

class KoinCheckTest : AutoCloseKoinTest() {

    private val mockAndroid = module {
        single { mockk<Context>() }
    }

    @Test
    fun `check Koin configuration`() {
        check(modules + mockAndroid)
    }
}
