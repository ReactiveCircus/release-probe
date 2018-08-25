package ychescale9.releaseprobe.base

import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import ychescale9.analytics.AnalyticsApi

abstract class BaseFragment : Fragment() {

    private val analyticsApi: AnalyticsApi by inject()

    override fun onResume() {
        super.onResume()
        activity?.let {
            analyticsApi.setCurrentScreenName(it, javaClass.simpleName, javaClass.simpleName)
        }
    }
}
