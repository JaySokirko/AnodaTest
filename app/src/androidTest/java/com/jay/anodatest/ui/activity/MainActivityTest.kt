package com.jay.anodatest.ui.activity

import android.content.Context
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.jay.anodatest.R
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    val appContext: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @JvmField
    @Rule
    val mainActivityTestRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java)

    @Test
    fun homeFragment_isAttached() {
        onView(withId(R.id.home_fragment_root_view)).check(matches(isDisplayed()))
    }

    @Test
    fun onHomeButtonPressed() {
        onView(withId(R.id.home)).perform(click())

        onView(withText("Home"))
            .inRoot(withDecorView(not(mainActivityTestRule.activity.window.decorView)))
            .check(matches(isDisplayed()))
    }

    //Без Thread.sleep(1000) тест не проходит. Как я понял, это из-за того, что предыдущий
    //Toast еще не исчез и закрывает Toast, который должен появиться при нажатии на camera_btn
    @Test
    fun onCameraBtnPressed() {
        Thread.sleep(1000)

        onView(withId(R.id.camera_btn)).perform(click())

        onView(withText("Open camera"))
            .inRoot(withDecorView(not(mainActivityTestRule.activity.window.decorView)))
            .check(matches(isDisplayed()))
    }
}