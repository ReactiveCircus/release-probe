package reactivecircus.releaseprobe.settings

import androidx.test.filters.LargeTest
import org.junit.Test
import reactivecircus.releaseprobe.testing.BaseScreenTest

@LargeTest
class SettingsScreenTest : BaseScreenTest() {

    @Test
    fun openSettingsScreenWithDefaultSettings_defaultSettingsDisplayed() {
        launchFragmentScenario<SettingsFragment>()
        // TODO
    }
}
