package reactivecircus.releaseprobe.browsecollections.robot

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import reactivecircus.blueprint.testing.BaseRobot
import reactivecircus.blueprint.testing.RobotActions
import reactivecircus.blueprint.testing.RobotAssertions
import reactivecircus.blueprint.testing.scrollToItemInRecyclerView
import reactivecircus.blueprint.testing.withRecyclerView
import reactivecircus.releaseprobe.browsecollections.R
import reactivecircus.releaseprobe.persistence.artifactcollection.entity.ArtifactCollectionEntity

fun browseArtifactCollectionsScreen(block: BrowseCollectionsRobot.() -> Unit) =
    BrowseCollectionsRobot().apply { block() }

class BrowseCollectionsRobot :
    BaseRobot<BrowseCollectionsRobotActions, BrowseCollectionsRobotAssertions>(
        BrowseCollectionsRobotActions(), BrowseCollectionsRobotAssertions()
    )

class BrowseCollectionsRobotActions : RobotActions() {

    fun clickArtifactCollection(position: Int) {
        clickRecyclerViewItem(R.id.artifactCollectionsRecyclerView, position)
    }
}

class BrowseCollectionsRobotAssertions : RobotAssertions() {

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
