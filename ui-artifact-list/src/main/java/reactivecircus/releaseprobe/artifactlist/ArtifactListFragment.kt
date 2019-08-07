package reactivecircus.releaseprobe.artifactlist

import android.os.Bundle
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import reactivecircus.releaseprobe.core.base.BaseFragment

const val ARG_COLLECTION_KEY = "ARG_COLLECTION_KEY"

class ArtifactListFragment : BaseFragment(R.layout.fragment_artifact_list) {

    private val viewModel by viewModel<ArtifactListViewModel> {
        parametersOf(
            requireNotNull(requireArguments().getString(ARG_COLLECTION_KEY))
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO
    }
}
