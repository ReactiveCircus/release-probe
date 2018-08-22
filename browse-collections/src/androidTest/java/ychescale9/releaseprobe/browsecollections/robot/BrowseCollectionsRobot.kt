package ychescale9.releaseprobe.browsecollections.robot

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.amshove.kluent.shouldEqual
import ychescale9.releaseprobe.browsecollections.R
import ychescale9.releaseprobe.persistence.artifactcollection.entity.ArtifactCollectionEntity
import ychescale9.uitest.BaseRobot
import ychescale9.uitest.RobotActions
import ychescale9.uitest.RobotAssertions
import ychescale9.uitest.currentActivity
import ychescale9.uitest.scrollToItemInRecyclerView
import ychescale9.uitest.withRecyclerView

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
        val recyclerView = requireNotNull(currentActivity())
                .findViewById(R.id.artifactCollectionsRecyclerView) as RecyclerView
        val recyclerViewId = R.id.artifactCollectionsRecyclerView
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
