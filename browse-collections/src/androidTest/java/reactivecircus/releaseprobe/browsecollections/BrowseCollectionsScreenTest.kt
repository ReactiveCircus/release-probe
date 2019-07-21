package reactivecircus.releaseprobe.browsecollections

import androidx.test.filters.LargeTest
import org.junit.Test
import reactivecircus.blueprint.testing.asViewAction
import reactivecircus.releaseprobe.browsecollections.robot.browseArtifactCollectionsScreen
import reactivecircus.releaseprobe.testing.BaseScreenTest

@LargeTest
class BrowseCollectionsScreenTest : BaseScreenTest() {

    @Test
    fun openBrowseCollectionsScreen_artifactCollectionsDisplayed() {
        browseArtifactCollectionsScreen {
            perform {
                launchFragmentScenario<BrowseCollectionsFragment>().asViewAction()
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
                launchFragmentScenario<BrowseCollectionsFragment>().asViewAction()
                // select the first artifact collection
                clickArtifactCollection(0)
            }
            // TODO
        }
    }
}
