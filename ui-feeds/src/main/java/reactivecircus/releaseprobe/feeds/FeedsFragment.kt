package reactivecircus.releaseprobe.feeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import reactivecircus.releaseprobe.core.base.BaseFragment
import reactivecircus.releaseprobe.feeds.databinding.FragmentFeedsBinding

class FeedsFragment : BaseFragment() {

    private lateinit var binding: FragmentFeedsBinding

//    private lateinit var feedsAdapter: FeedsAdapter

    private val viewModel by viewModel<FeedsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentFeedsBinding.inflate(inflater, container, false).let {
        binding = it
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO
    }
}
