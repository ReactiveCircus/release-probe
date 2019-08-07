package reactivecircus.releaseprobe.artifactlist

import androidx.core.os.bundleOf
import androidx.test.filters.LargeTest
import org.junit.Test
import reactivecircus.releaseprobe.testing.BaseScreenTest

@LargeTest
class ArtifactListScreenTest : BaseScreenTest() {

    @Test
    fun openArtifactListScreenWithCollectionKey_artifactListDisplayed() {
        launchFragmentScenario<ArtifactListFragment>(
            bundleOf(ARG_COLLECTION_KEY to "AndroidX") // TODO use 1st item in test data
        )
        // TODO
    }
}
