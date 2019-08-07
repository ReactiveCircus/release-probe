package reactivecircus.releaseprobe.watchlist

import android.os.Bundle
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import reactivecircus.releaseprobe.core.base.BaseFragment

class WatchlistFragment : BaseFragment(R.layout.fragment_watchlist) {

//    private lateinit var watchlistAdapter: WatchlistAdapter

    private val viewModel by viewModel<WatchlistViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO
    }
}
