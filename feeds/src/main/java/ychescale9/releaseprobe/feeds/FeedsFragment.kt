package ychescale9.releaseprobe.feeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ychescale9.releaseprobe.core.base.BaseFragment

class FeedsFragment : BaseFragment() {

//    private lateinit var feedsAdapter: FeedsAdapter
//
//    private val viewModel: FeedsViewModel
//
//    private val animationHelper: AnimationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_feeds, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO
    }
}
