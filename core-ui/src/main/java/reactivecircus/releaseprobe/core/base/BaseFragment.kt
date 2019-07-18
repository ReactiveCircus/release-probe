package reactivecircus.releaseprobe.core.base

import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import reactivecircus.analytics.AnalyticsApi

abstract class BaseFragment : Fragment() {

    private val analyticsApi: AnalyticsApi by inject()

    override fun onResume() {
        super.onResume()
        activity?.run {
            analyticsApi.setCurrentScreenName(this, javaClass.simpleName, javaClass.simpleName)
        }
    }
}
