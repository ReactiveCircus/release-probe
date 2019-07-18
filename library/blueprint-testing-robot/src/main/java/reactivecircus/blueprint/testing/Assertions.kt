package reactivecircus.blueprint.testing

import android.app.Activity
import android.view.View
import android.widget.RadioGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers

class RadioGroupAssertion : ViewAssertion {

    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val radioGroup = view as RadioGroup
        MatcherAssert.assertThat(radioGroup.checkedRadioButtonId, Matchers.greaterThanOrEqualTo(0))
    }

    companion object {

        fun hasSelections(): RadioGroupAssertion {
            return RadioGroupAssertion()
        }
    }
}

class RecyclerViewItemCountAssertion(private val expectedCount: Int) : ViewAssertion {

    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        MatcherAssert.assertThat(adapter?.itemCount, CoreMatchers.equalTo(expectedCount))
    }

    companion object {

        fun hasSize(expectedCount: Int): RecyclerViewItemCountAssertion {
            return RecyclerViewItemCountAssertion(expectedCount)
        }
    }
}

class ActivityNotDisplayedAssertion(private val activity: Activity?) : ViewAssertion {

    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        activity?.run {
            MatcherAssert.assertThat(isFinishing, CoreMatchers.equalTo(true))
        }
    }

    companion object {

        fun activityNotDisplayed(activity: Activity?): ActivityNotDisplayedAssertion {
            return ActivityNotDisplayedAssertion(activity)
        }
    }
}
