package reactivecircus.releaseprobe.browsecollections.robot

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import reactivecircus.blueprint.testing.RobotActions
import reactivecircus.blueprint.testing.RobotAssertions
import reactivecircus.blueprint.testing.ScreenRobot
import reactivecircus.blueprint.testing.assertion.recyclerViewHasSize
import reactivecircus.blueprint.testing.matcher.withRecyclerView
import reactivecircus.blueprint.testing.scrollToItemInRecyclerView
import reactivecircus.releaseprobe.browsecollections.R
import reactivecircus.releaseprobe.persistence.artifactcollection.entity.ArtifactCollectionEntity

fun browseArtifactCollectionsScreen(block: BrowseCollectionsRobot.() -> Unit) =
    BrowseCollectionsRobot().apply { block() }

class BrowseCollectionsRobot :
    ScreenRobot<BrowseCollectionsRobot.Actions, BrowseCollectionsRobot.Assertions>(
        Actions(), Assertions()
    ) {

    class Actions : RobotActions

    class Assertions : RobotAssertions {

        fun artifactCollectionsDisplayed(artifactCollections: List<ArtifactCollectionEntity>) {
            val recyclerViewId = R.id.artifactCollectionsRecyclerView
            val artifactCollectionNameTextViewId = R.id.artifactCollectionNameTextView
            val artifactCollectionDescriptionTextViewId = R.id.artifactCollectionDescriptionTextView

            recyclerViewHasSize(recyclerViewId, artifactCollections.size)

            artifactCollections.forEachIndexed { index, artifactCollection ->
                // scroll to the item to make sure it's visible
                scrollToItemInRecyclerView(recyclerViewId, index)

                onView(
                    withRecyclerView(recyclerViewId)
                        .atPositionOnView(index, artifactCollectionNameTextViewId)
                )
                    .check(matches(withText(artifactCollection.name)))

                onView(
                    withRecyclerView(recyclerViewId)
                        .atPositionOnView(index, artifactCollectionDescriptionTextViewId)
                )
                    .check(matches(withText(artifactCollection.description)))
            }
        }
    }
}
