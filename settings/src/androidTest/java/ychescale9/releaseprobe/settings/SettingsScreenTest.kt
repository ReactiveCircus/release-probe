package ychescale9.releaseprobe.settings

import androidx.test.filters.LargeTest
import org.junit.Test
import ychescale9.releaseprobe.testing.BaseScreenTest

@LargeTest
class SettingsScreenTest : BaseScreenTest() {

    @Test
    fun openSettingsScreenWithDefaultSettings_defaultSettingsDisplayed() {
        launchFragmentScenario<SettingsFragment>()
        // TODO
    }
}
