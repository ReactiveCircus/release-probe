package ychescale9.releaseprobe.feeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ychescale9.releaseprobe.base.BaseFragment

class FeedsFragment : BaseFragment() {

//    private lateinit var feedsAdapter: FeedsAdapter
//
//    @Inject
//    lateinit var viewModel: FeedsViewModel
//
//    @Inject
//    lateinit var animationHelper: AnimationHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_feeds, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO
    }
}
