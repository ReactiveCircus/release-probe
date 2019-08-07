package reactivecircus.releaseprobe.feeds

import android.os.Bundle
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import reactivecircus.releaseprobe.core.base.BaseFragment

class FeedsFragment : BaseFragment(R.layout.fragment_feeds) {

//    private lateinit var feedsAdapter: FeedsAdapter

    private val viewModel by viewModel<FeedsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO
    }
}
