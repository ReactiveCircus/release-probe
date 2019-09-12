package reactivecircus.releaseprobe.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import reactivecircus.releaseprobe.core.base.BaseFragment
import reactivecircus.releaseprobe.settings.databinding.FragmentSettingsBinding

class SettingsFragment : BaseFragment() {

    private lateinit var binding: FragmentSettingsBinding

    private val viewModel by viewModel<SettingsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSettingsBinding.inflate(inflater, container, false).let {
        binding = it
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO
    }
}
