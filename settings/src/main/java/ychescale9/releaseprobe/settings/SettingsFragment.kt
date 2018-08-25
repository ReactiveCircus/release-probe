package ychescale9.releaseprobe.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.scope.ext.android.scopedWith
import org.koin.dsl.path.moduleName
import ychescale9.releaseprobe.base.BaseFragment

class SettingsFragment : BaseFragment() {

//    private val viewModel by viewModel<SettingsViewModel>()
//
//    private val animationHelper: AnimationHelper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scopedWith(javaClass.kotlin.moduleName)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO
    }
}
