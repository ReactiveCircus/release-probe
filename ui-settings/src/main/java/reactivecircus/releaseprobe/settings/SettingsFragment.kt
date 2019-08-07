package reactivecircus.releaseprobe.settings

import android.os.Bundle
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import reactivecircus.releaseprobe.core.base.BaseFragment

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    private val viewModel by viewModel<SettingsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO
    }
}
