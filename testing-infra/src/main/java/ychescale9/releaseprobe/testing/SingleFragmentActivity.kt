package ychescale9.releaseprobe.testing

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import dagger.android.support.DaggerAppCompatActivity

/**
 * Used for testing a fragment inside an activity.
 */
class SingleFragmentActivity : DaggerAppCompatActivity() {

    private val containerId = View.generateViewId()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val content = FrameLayout(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            id = containerId
        }
        setContentView(content)
    }

    fun setFragment(fragment: Fragment) {
        supportFragmentManager.transaction(allowStateLoss = true) {
            replace(containerId, fragment)
        }
    }
}
