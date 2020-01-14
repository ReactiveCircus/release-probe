package reactivecircus.releaseprobe.feeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import reactivecircus.releaseprobe.core.base.BaseFragment
import reactivecircus.releaseprobe.feeds.databinding.FragmentFeedsBinding

class FeedsFragment : BaseFragment<FragmentFeedsBinding>() {

//    private lateinit var feedsAdapter: FeedsAdapter

    private val viewModel by viewModel<FeedsViewModel>()

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFeedsBinding {
        return FragmentFeedsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO
    }
}
