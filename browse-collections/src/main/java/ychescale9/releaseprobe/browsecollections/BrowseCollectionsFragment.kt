package ychescale9.releaseprobe.browsecollections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_browse_collections.*
import ychescale9.releaseprobe.base.BaseFragment
import ychescale9.releaseprobe.domain.artifactcollection.model.ArtifactCollection
import ychescale9.releaseprobe.util.AnimationHelper

class BrowseCollectionsFragment : BaseFragment() {

    private lateinit var artifactCollectionsAdapter: ArtifactCollectionsAdapter

    @Inject
    lateinit var viewModel: ArtifactCollectionsViewModel

    @Inject
    lateinit var animationHelper: AnimationHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_browse_collections, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        artifactCollectionsAdapter = ArtifactCollectionsAdapter(actionListener, animationHelper)
        artifactCollectionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = artifactCollectionsAdapter
        }

        viewModel.artifactCollectionsLiveData.observe(this, Observer {
            artifactCollectionsAdapter.submitList(it)
        })
    }

    private val actionListener: ArtifactCollectionsAdapter.ActionListener =
            object : ArtifactCollectionsAdapter.ActionListener {
                override fun onItemClick(artifactCollection: ArtifactCollection) {
                    // TODO
                }
            }
}
