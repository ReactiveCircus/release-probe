package reactivecircus.releaseprobe.browsecollections

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_browse_collections.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import reactivecircus.releaseprobe.core.AppNavigator
import reactivecircus.releaseprobe.core.base.BaseFragment
import reactivecircus.releaseprobe.domain.artifactcollection.model.ArtifactCollection

class BrowseCollectionsFragment : BaseFragment(R.layout.fragment_browse_collections) {

    private lateinit var artifactCollectionsAdapter: ArtifactCollectionsAdapter

    private val navigator by inject<AppNavigator>()

    private val viewModel by viewModel<BrowseCollectionsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        artifactCollectionsAdapter = ArtifactCollectionsAdapter(
            actionListener = actionListener,
            animate = savedInstanceState == null
        )
        artifactCollectionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = artifactCollectionsAdapter
        }

        viewModel.artifactCollectionsLiveData.observe(this) {
            artifactCollectionsAdapter.submitList(it)
        }
    }

    private val actionListener: ArtifactCollectionsAdapter.ActionListener =
        object : ArtifactCollectionsAdapter.ActionListener {
            override fun onItemClick(artifactCollection: ArtifactCollection) {
                navigator.navigateToArtifactListScreen(requireActivity(), artifactCollection.name)
            }
        }
}
