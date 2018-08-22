package ychescale9.releaseprobe.browsecollections

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import ychescale9.releaseprobe.browsecollections.robot.browseArtifactCollectionsScreen
import ychescale9.releaseprobe.testing.BaseScreenTest
import ychescale9.releaseprobe.testing.SingleFragmentActivity

@LargeTest
class BrowseCollectionsScreenTest : BaseScreenTest() {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, false)

    @Test
    fun openBrowseCollectionsScreen_artifactCollectionsDisplayed() {
        browseArtifactCollectionsScreen {
            perform {
                launchActivityWithFragment(activityRule, BrowseCollectionsFragment())
            }
            check {
                artifactCollectionsDisplayed(defaultArtifactCollections.get())
            }
        }
    }

    @Test
    fun clickArtifactCollection_browseArtifactsScreenLaunched() {
        browseArtifactCollectionsScreen {
            perform {
                launchActivityWithFragment(activityRule, BrowseCollectionsFragment())
                // select the first artifact collection
                clickArtifactCollection(0)
            }
            // TODO
        }
    }
}
