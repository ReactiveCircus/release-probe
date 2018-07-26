package ychescale9.releaseprobe

import ychescale9.releaseprobe.di.component.DaggerMainTestAppComponent
import ychescale9.releaseprobe.testing.ScreenTestApp
import ychescale9.releaseprobe.testing.di.component.ScreenTestAppComponent

class MainTestApp : ScreenTestApp() {

    override fun loadTestAppComponent(): ScreenTestAppComponent {
        return DaggerMainTestAppComponent.builder()
                .testApp(this)
                .build()
    }
}
