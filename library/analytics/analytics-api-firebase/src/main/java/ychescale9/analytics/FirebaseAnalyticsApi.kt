package ychescale9.analytics

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseAnalyticsApi(context: Context) : AnalyticsApi {

    private val firebaseAnalytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(context)

    /**
     * By default Firebase uses the class name of the activity for automatic screen reporting.
     * Call this in Activity.onResume() to set a custom name for the current screen.
     * Also call this to define new screens within an Activity triggered by changing Fragments, navigating in ViewPager, etc.
     */
    override fun setCurrentScreenName(activity: Activity, name: String, className: String?) {
        firebaseAnalytics.setCurrentScreen(activity, name, className)
    }

    override fun setEnableAnalytics(enable: Boolean) {
        firebaseAnalytics.setAnalyticsCollectionEnabled(enable)
    }

    override fun setUserId(userId: String?) {
        firebaseAnalytics.setUserId(userId)
    }

    override fun setUserProperty(name: String, value: String) {
        firebaseAnalytics.setUserProperty(name, value)
    }

    override fun logEvent(name: String, params: Map<String, *>?) {
        val bundle = Bundle().takeIf { params != null }
                ?.apply {
                    params?.apply {
                        for (entry in entries) {
                            when (entry.value) {
                                is String -> putString(entry.key, (entry.value as String))
                                is Long -> putLong(entry.key, (entry.value as Long))
                                is Double -> putDouble(entry.key, (entry.value as Double))
                            }
                        }
                    }
                }

        firebaseAnalytics.logEvent(name, bundle)
    }
}
