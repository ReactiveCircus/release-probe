package ychescale9.releaseprobe.browsecollections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_browse_collections.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ychescale9.releaseprobe.base.BaseFragment
import ychescale9.releaseprobe.domain.artifactcollection.model.ArtifactCollection
import ychescale9.releaseprobe.util.AnimationHelper

class BrowseCollectionsFragment : BaseFragment() {

    private lateinit var artifactCollectionsAdapter: ArtifactCollectionsAdapter

    private val viewModel by viewModel<ArtifactCollectionsViewModel>()

    private val animationHelper: AnimationHelper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
