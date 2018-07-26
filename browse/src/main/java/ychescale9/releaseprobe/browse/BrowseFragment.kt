package ychescale9.releaseprobe.browse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ychescale9.releaseprobe.base.BaseFragment
import javax.inject.Inject

class BrowseFragment : BaseFragment() {

//    private lateinit var artifactCollectionsAdapter: ArtifactCollectionsAdapter

    @Inject
    lateinit var viewModel: ArtifactCollectionsViewModel

//    @Inject
//    lateinit var animationHelper: AnimationHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_browse, container, false)

        // TODO

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO
    }
}
