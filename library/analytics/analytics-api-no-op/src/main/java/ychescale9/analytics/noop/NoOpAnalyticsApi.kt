package ychescale9.analytics.noop

import android.app.Activity
import ychescale9.analytics.AnalyticsApi

fun Activity.setCurrentScreenName(name: String) = Unit

class NoOpAnalyticsApi : AnalyticsApi {

    override fun setCurrentScreenName(activity: Activity, name: String, className: String?) {
    }

    override fun setEnableAnalytics(enable: Boolean) {
    }

    override fun setUserId(userId: String?) {
    }

    override fun setUserProperty(name: String, value: String) {
    }

    override fun logEvent(name: String, params: Map<String, *>?) {
    }
}
