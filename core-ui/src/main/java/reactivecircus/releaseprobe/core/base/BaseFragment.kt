package reactivecircus.releaseprobe.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import org.koin.android.ext.android.inject
import reactivecircus.analytics.AnalyticsApi

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {

    private val analyticsApi: AnalyticsApi by inject()

    private var _binding: Binding? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = provideViewBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): Binding

    override fun onResume() {
        super.onResume()
        activity?.run {
            analyticsApi.setCurrentScreenName(this, javaClass.simpleName, javaClass.simpleName)
        }
    }
}
