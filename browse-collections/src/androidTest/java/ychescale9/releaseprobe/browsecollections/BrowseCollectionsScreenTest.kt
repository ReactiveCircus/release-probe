package ychescale9.releaseprobe.browsecollections

import androidx.test.filters.LargeTest
import org.junit.Test
import ychescale9.releaseprobe.browsecollections.robot.browseArtifactCollectionsScreen
import ychescale9.releaseprobe.testing.BaseScreenTest

@LargeTest
class BrowseCollectionsScreenTest : BaseScreenTest() {

    @Test
    fun openBrowseCollectionsScreen_artifactCollectionsDisplayed() {
        browseArtifactCollectionsScreen {
            perform {
                launchFragmentScenario<BrowseCollectionsFragment>()
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
                launchFragmentScenario<BrowseCollectionsFragment>()
                // select the first artifact collection
                clickArtifactCollection(0)
            }
            // TODO
        }
    }
}
