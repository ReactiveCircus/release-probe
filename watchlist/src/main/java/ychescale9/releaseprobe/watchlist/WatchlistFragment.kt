package ychescale9.releaseprobe.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ychescale9.releaseprobe.base.BaseFragment

class WatchlistFragment : BaseFragment() {

//    private lateinit var watchlistAdapter: WatchlistAdapter
//
//    @Inject
//    lateinit var viewModel: WatchlistViewModel
//
//    @Inject
//    lateinit var animationHelper: AnimationHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_watchlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO
    }
}
