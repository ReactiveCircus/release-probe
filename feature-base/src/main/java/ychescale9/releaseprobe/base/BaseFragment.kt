package ychescale9.releaseprobe.base

import dagger.android.support.DaggerFragment
import javax.inject.Inject
import ychescale9.analytics.AnalyticsApi

abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var analyticsApi: AnalyticsApi

    override fun onResume() {
        super.onResume()
        activity?.let {
            analyticsApi.setCurrentScreenName(it, javaClass.simpleName, javaClass.simpleName)
        }
    }
}
