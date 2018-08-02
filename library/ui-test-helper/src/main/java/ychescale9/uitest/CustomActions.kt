package ychescale9.uitest

import androidx.annotation.IdRes
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.allOf

private const val MAX_SCROLL_ATTEMPTS = 100

fun scrollTo(@IdRes id: Int) {
    ViewActions.repeatedlyUntil(
            ViewActions.scrollTo(),
            allOf(withId(id), isDisplayed()),
            MAX_SCROLL_ATTEMPTS
    )
}

fun scrollTo(text: String) {
    ViewActions.repeatedlyUntil(
            ViewActions.scrollTo(),
            allOf(withText(text), isDisplayed()),
            MAX_SCROLL_ATTEMPTS
    )
}
