package ychescale9.analytics.noop

import android.app.Activity
import ychescale9.analytics.AnalyticsApi

fun Activity.setCurrentScreenName(name: String) = Unit

class NoOpAnalyticsApi : AnalyticsApi {

    override fun setCurrentScreenName(activity: Activity, name: String, className: String?) = Unit

    override fun setEnableAnalytics(enable: Boolean) = Unit

    override fun setUserId(userId: String?) = Unit

    override fun setUserProperty(name: String, value: String) = Unit

    override fun logEvent(name: String, params: Map<String, *>?) = Unit
}
