package reactivecircus.releaseprobe.browsecollections.robot

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.amshove.kluent.shouldEqual
import reactivecircus.releaseprobe.browsecollections.R
import reactivecircus.releaseprobe.persistence.artifactcollection.entity.ArtifactCollectionEntity
import reactivecircus.blueprint.testing.BaseRobot
import reactivecircus.blueprint.testing.RobotActions
import reactivecircus.blueprint.testing.RobotAssertions
import reactivecircus.blueprint.testing.currentActivity
import reactivecircus.blueprint.testing.scrollToItemInRecyclerView
import reactivecircus.blueprint.testing.withRecyclerView

fun browseArtifactCollectionsScreen(block: BrowseCollectionsRobot.() -> Unit) =
        BrowseCollectionsRobot().apply { block() }

class BrowseCollectionsRobot : BaseRobot<BrowseCollectionsRobotActions, BrowseCollectionsRobotAssertions>(
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
        val recyclerView: RecyclerView = requireNotNull(currentActivity()).findViewById(recyclerViewId)
        val artifactCollectionNameTextViewId = R.id.artifactCollectionNameTextView
        val artifactCollectionDescriptionTextViewId = R.id.artifactCollectionDescriptionTextView
        val adapter = requireNotNull(recyclerView.adapter)

        (adapter.itemCount > 0) shouldEqual true

        for (index in 0 until adapter.itemCount) {
            // scroll to the item to make sure it's visible
            scrollToItemInRecyclerView(recyclerViewId, index)

            val artifactCollection = artifactCollections[index]

            onView(withRecyclerView(recyclerViewId)
                    .atPositionOnView(index, artifactCollectionNameTextViewId))
                    .check(matches(withText(artifactCollection.name)))

            onView(withRecyclerView(recyclerViewId)
                    .atPositionOnView(index, artifactCollectionDescriptionTextViewId))
                    .check(matches(withText(artifactCollection.description)))
        }
    }
}
