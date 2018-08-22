@file:Suppress("TooManyFunctions")

package ychescale9.uitest

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.core.AllOf
import org.hamcrest.core.Is
import org.hamcrest.core.IsNot
import org.hamcrest.core.StringContains
import org.hamcrest.core.StringStartsWith
import org.junit.Assert

abstract class BaseRobot<out A : RobotActions, out S : RobotAssertions>(
    private val robotActions: A,
    private val robotAssertions: S
) {
    fun given(block: () -> Unit) = block()
    fun perform(block: A.() -> Unit) = robotActions.apply { block() }
    fun check(block: S.() -> Unit) = robotAssertions.apply { block() }
}

open class RobotActions {

    fun clickView(@IdRes viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId)).perform(ViewActions.click())
    }

    fun longClickView(@IdRes viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId)).perform(ViewActions.longClick())
    }

    fun enterTextIntoView(@IdRes viewId: Int, text: String) {
        Espresso.onView(ViewMatchers.withId(viewId)).perform(ViewActions.typeText(text))
    }

    fun replaceTextInView(@IdRes viewId: Int, text: String) {
        Espresso.onView(ViewMatchers.withId(viewId)).perform(ViewActions.replaceText(text))
    }

    fun clearTextInView(@IdRes viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId)).perform(ViewActions.clearText())
    }

    fun closeKeyboard(@IdRes viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId)).perform(ViewActions.closeSoftKeyboard())
    }

    fun pressKeyboardActionButton(@IdRes viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId)).perform(ViewActions.pressImeActionButton())
    }

    fun swipeLeftOnView(@IdRes viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId)).perform(ViewActions.swipeLeft())
    }

    fun swipeRightOnView(@IdRes viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId)).perform(ViewActions.swipeRight())
    }

    fun swipeUpOnView(@IdRes viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId)).perform(ViewActions.swipeUp())
    }

    fun swipeDownOnView(@IdRes viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId)).perform(ViewActions.swipeDown())
    }

    fun clickDialogButton1() {
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click())
    }

    fun clickDialogButton2() {
        Espresso.onView(ViewMatchers.withId(android.R.id.button2)).perform(ViewActions.click())
    }

    fun clickDialogButton3() {
        Espresso.onView(ViewMatchers.withId(android.R.id.button3)).perform(ViewActions.click())
    }

    fun clickRecyclerViewItem(@IdRes recyclerViewId: Int, position: Int) {
        // scroll to the item to make sure it's visible
        scrollToItemInRecyclerView(recyclerViewId, position)

        Espresso.onView(ViewMatchers.withId(recyclerViewId))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        position, ViewActions.click()))
    }

    fun clickRadioButton(@IdRes radioGroupId: Int, buttonText: String) {
        scrollTo(buttonText)
        Espresso.onView(AllOf.allOf(ViewMatchers.withParent(
                AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(radioGroupId))),
                AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withText(buttonText)))
        ).perform(ViewActions.click())
    }

    fun selectCheckBox(@IdRes layoutId: Int, text: String) {
        scrollTo(text)
        Espresso.onView(AllOf.allOf(ViewMatchers.withParent(
                AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(layoutId))),
                AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withText(text)))
        ).perform(ViewActions.click())
    }

    fun selectBottomNavigationItem(@IdRes bottomNavigationViewResId: Int, navItemTitle: String) {
        Espresso.onView(CoreMatchers.allOf(ViewMatchers.withId(com.google.android.material.R.id.icon),
                ViewMatchers.hasSibling(ViewMatchers.hasDescendant(ViewMatchers.withText(navItemTitle))),
                ViewMatchers.isDescendantOfA(ViewMatchers.withId(bottomNavigationViewResId))))
                .perform(ViewActions.click())
    }

    fun pressBack() {
        Espresso.pressBackUnconditionally()
    }

    fun clickNavigateUpButton() {
        Espresso.onView(withToolbarNavigationButton()).perform(ViewActions.click())
    }

    fun openDrawer(@IdRes drawerId: Int) {
        Espresso.onView(ViewMatchers.withId(drawerId)).perform(DrawerActions.open())
    }

    fun closeDrawer(@IdRes drawerId: Int) {
        Espresso.onView(ViewMatchers.withId(drawerId)).perform(DrawerActions.close())
    }
}

open class RobotAssertions {

    fun viewDisplayed(@IdRes vararg viewIds: Int) {
        viewIds.forEach {
            viewId -> Espresso.onView(ViewMatchers.withId(viewId))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }

    fun viewNotDisplayed(@IdRes vararg viewIds: Int) {
        viewIds.forEach {
            viewId -> Espresso.onView(ViewMatchers.withId(viewId))
                .check(ViewAssertions.matches(IsNot.not<View>(ViewMatchers.isDisplayed())))
        }
    }

    fun textDisplayed(@StringRes vararg textResIds: Int) {
        textResIds.forEach {
            textResId -> Espresso.onView(ViewMatchers.withText(textResId))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }

    fun textDisplayed(vararg texts: String) {
        texts.forEach {
            text -> Espresso.onView(ViewMatchers.withText(text))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }

    fun viewHasText(@IdRes viewId: Int, expected: String) {
        Espresso.onView(ViewMatchers.withId(viewId))
                .check(ViewAssertions.matches(ViewMatchers.withText(expected)))
    }

    fun viewHasText(@IdRes viewId: Int, @StringRes messageResId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId))
                .check(ViewAssertions.matches(ViewMatchers.withText(messageResId)))
    }

    fun viewContainsText(@IdRes viewId: Int, expected: String) {
        Espresso.onView(ViewMatchers.withId(viewId))
                .check(ViewAssertions.matches(ViewMatchers.withText(StringContains.containsString(expected))))
    }

    fun viewStartsWithText(expected: String, @IdRes parentResId: Int) {
        Espresso.onView(
                AllOf.allOf<View>(
                        ViewMatchers.withText(StringStartsWith.startsWith(expected)),
                        ViewMatchers.isDescendantOfA(ViewMatchers.withId(parentResId))))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun viewHasHint(@IdRes viewId: Int, @StringRes messageResId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId))
                .check(ViewAssertions.matches(ViewMatchers.withHint(messageResId)))
    }

    fun textInputLayoutHasError(@IdRes viewId: Int, errorMessage: String) {
        Espresso.onView(ViewMatchers.withId(viewId))
                .check(ViewAssertions.matches(hasTextInputLayoutErrorText(errorMessage)))
    }

    fun textInputLayoutHasError(@IdRes viewId: Int, @StringRes errorMessageResId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId))
                .check(ViewAssertions.matches(hasTextInputLayoutErrorText(errorMessageResId)))
    }

    fun textInputLayoutHasNoError(@IdRes viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId))
                .check(ViewAssertions.matches(noTextInputLayoutError()))
    }

    fun keyboardInputTypeIsEmail(@IdRes viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId))
                .check(ViewAssertions.matches(withEmailInputType()))
    }

    fun viewEnabled(@IdRes viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId))
                .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }

    fun viewDisabled(@IdRes viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId))
                .check(ViewAssertions.matches(IsNot.not<View>(ViewMatchers.isEnabled())))
    }

    fun viewClickable(@IdRes viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId))
                .check(ViewAssertions.matches(ViewMatchers.isClickable()))
    }

    fun viewNotClickable(@IdRes viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId))
                .check(ViewAssertions.matches(IsNot.not<View>(ViewMatchers.isClickable())))
    }

    fun dialogWithButton1Displayed(@StringRes buttonTextResId: Int) {
        Espresso.onView(ViewMatchers.withId(android.R.id.button1))
                .inRoot(RootMatchers.isDialog())
                .check(ViewAssertions.matches(ViewMatchers.withText(buttonTextResId)))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun dialogWithButton2Displayed(@StringRes buttonTextResId: Int) {
        Espresso.onView(ViewMatchers.withId(android.R.id.button2))
                .inRoot(RootMatchers.isDialog())
                .check(ViewAssertions.matches(ViewMatchers.withText(buttonTextResId)))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun dialogWithButton3Displayed(@StringRes buttonTextResId: Int) {
        Espresso.onView(ViewMatchers.withId(android.R.id.button3))
                .inRoot(RootMatchers.isDialog())
                .check(ViewAssertions.matches(ViewMatchers.withText(buttonTextResId)))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun recyclerViewHasSize(@IdRes recyclerViewId: Int, size: Int) {
        Espresso.onView(AllOf.allOf<View>(ViewMatchers.withId(recyclerViewId), ViewMatchers.isDisplayed()))
                .check(RecyclerViewItemCountAssertion.hasSize(size))
    }

    fun viewDisplayedInRecyclerView(@IdRes recyclerViewId: Int, @IdRes vararg viewIds: Int) {
        val recyclerView = requireNotNull(currentActivity()).findViewById(recyclerViewId) as RecyclerView
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val visibleCount = layoutManager.findLastVisibleItemPosition() + 1

        Assert.assertTrue(visibleCount > 0)
        (0 until visibleCount).forEach { index ->
            // scroll to the item to make sure it's visible
            Espresso.onView(AllOf.allOf(ViewMatchers.withId(recyclerViewId), ViewMatchers.isDisplayed()))
                    .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(index))

            viewIds.forEach { viewId ->
                Espresso.onView(withRecyclerView(recyclerViewId)
                        .atPositionOnView(index, viewId))
                        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            }
        }
    }

    fun toolbarHasTitle(title: String) {
        Espresso.onView(ViewMatchers.isAssignableFrom(Toolbar::class.java))
                .check(ViewAssertions.matches(withToolbarTitle(Is.`is`<CharSequence>(title))))
    }

    fun toolbarHasTitle(@StringRes titleTextResId: Int) {
        Espresso.onView(ViewMatchers.isAssignableFrom(Toolbar::class.java))
                .check(ViewAssertions.matches(
                        withToolbarTitle(Is.`is`<CharSequence>(
                                getTargetContext().getString(titleTextResId)))))
    }

    fun toolbarHasSubtitle(subtitle: String) {
        Espresso.onView(ViewMatchers.isAssignableFrom(Toolbar::class.java))
                .check(ViewAssertions.matches(withToolbarSubtitle(Is.`is`<CharSequence>(subtitle))))
    }

    fun scrollableViewHasText(@IdRes radioGroupId: Int, vararg buttonTexts: String) {
        buttonTexts.forEach { buttonText ->
            scrollTo(buttonText)
            Espresso.onView(AllOf.allOf(ViewMatchers.withParent(
                    AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(radioGroupId))),
                    AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withText(buttonText)))
            ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }

    fun checkableViewSelected(@IdRes radioGroupId: Int, buttonText: String) {
        scrollTo(buttonText)
        Espresso.onView(AllOf.allOf(ViewMatchers.withParent(
                AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(radioGroupId))),
                AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withText(buttonText)))
        ).check(ViewAssertions.matches(ViewMatchers.isChecked()))
    }

    fun radioGroupHasSelections(@IdRes radioGroupId: Int) {
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(radioGroupId)))
                .check(RadioGroupAssertion.hasSelections())
    }

    fun radioGroupHasNoSelections(@IdRes radioGroupId: Int) {
        Espresso.onView(AllOf.allOf(ViewMatchers.isDisplayed(), ViewMatchers.withId(radioGroupId)))
                .check(ViewAssertions.matches(IsNot.not<Any>(RadioGroupAssertion.hasSelections())))
    }

    fun drawableDisplayed(@IdRes resourceId: Int) {
        Espresso.onView(withDrawable(resourceId))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun bottomNavigationViewItemSelected(@IdRes bottomNavigationViewResId: Int, @IdRes selectedItemResId: Int) {
        Espresso.onView(ViewMatchers.withId(bottomNavigationViewResId))
                .check(ViewAssertions.matches(hasSelectedNavigationItem(selectedItemResId)))
    }

    fun drawerOpened(@IdRes drawerId: Int) {
        Espresso.onView(ViewMatchers.withId(drawerId))
                .check(ViewAssertions.matches(DrawerMatchers.isOpen()))
    }

    fun drawerClosed(@IdRes drawerId: Int) {
        Espresso.onView(ViewMatchers.withId(drawerId))
                .check(ViewAssertions.matches(DrawerMatchers.isClosed()))
    }

    fun snackBarDisplayed(text: String) {
        Espresso.onView(allOf(
                ViewMatchers.withId(com.google.android.material.R.id.snackbar_text),
                ViewMatchers.withText(text)))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun viewChecked(@IdRes viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId))
                .check(ViewAssertions.matches(ViewMatchers.isChecked()))
    }

    fun viewNotChecked(@IdRes viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId))
                .check(ViewAssertions.matches(ViewMatchers.isNotChecked()))
    }

    fun spinnerHasText(@IdRes spinnerId: Int, text: String) {
        Espresso.onView(ViewMatchers.withId(spinnerId))
                .check(ViewAssertions.matches(ViewMatchers.withSpinnerText(text)))
    }

    fun noScreenDisplayed() {
        Assert.assertTrue(currentActivity() == null)
    }

    fun screenLaunched(activityClass: Class<out Activity>) {
        Intents.intended(IntentMatchers.hasComponent(activityClass.name))
    }
}
