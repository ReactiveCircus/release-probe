package ychescale9.releaseprobe.testing

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import androidx.test.platform.app.InstrumentationRegistry

/**
 * Used for testing a fragment inside an activity.
 */
class SingleFragmentActivity : AppCompatActivity() {

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
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            supportFragmentManager.commitNow(allowStateLoss = true) {
                replace(containerId, fragment)
            }
        }
    }
}
