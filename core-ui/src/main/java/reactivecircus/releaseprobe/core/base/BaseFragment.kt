package reactivecircus.releaseprobe.core.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import reactivecircus.analytics.AnalyticsApi

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    private val analyticsApi: AnalyticsApi by inject()

    override fun onResume() {
        super.onResume()
        activity?.run {
            analyticsApi.setCurrentScreenName(javaClass.simpleName, javaClass.simpleName)
        }
    }
}
