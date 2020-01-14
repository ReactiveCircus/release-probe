package reactivecircus.releaseprobe.artifactlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import reactivecircus.releaseprobe.artifactlist.databinding.FragmentArtifactListBinding
import reactivecircus.releaseprobe.core.base.BaseFragment

const val ARG_COLLECTION_KEY = "ARG_COLLECTION_KEY"

class ArtifactListFragment : BaseFragment<FragmentArtifactListBinding>() {

    private val viewModel by viewModel<ArtifactListViewModel> {
        parametersOf(
            requireNotNull(requireArguments().getString(ARG_COLLECTION_KEY))
        )
    }

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentArtifactListBinding {
        return FragmentArtifactListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO
    }
}
