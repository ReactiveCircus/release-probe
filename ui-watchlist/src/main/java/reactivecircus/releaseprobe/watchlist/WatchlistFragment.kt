package reactivecircus.releaseprobe.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import reactivecircus.releaseprobe.core.base.BaseFragment
import reactivecircus.releaseprobe.watchlist.databinding.FragmentWatchlistBinding

class WatchlistFragment : BaseFragment() {

    private lateinit var binding: FragmentWatchlistBinding

//    private lateinit var watchlistAdapter: WatchlistAdapter

    private val viewModel by viewModel<WatchlistViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentWatchlistBinding.inflate(inflater, container, false).let {
        binding = it
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO
    }
}
