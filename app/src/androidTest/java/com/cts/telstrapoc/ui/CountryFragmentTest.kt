package com.cts.telstrapoc.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.cts.telstrapoc.MainActivity
import com.cts.telstrapoc.R
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class CountryFragmentTest {

    @Test
    fun checkFragmentPageLoaded() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // check host fragment got loaded on screen
        Espresso.onView(ViewMatchers.withId(R.id.parent_layout)).check(ViewAssertions.matches(
            ViewMatchers.isDisplayed()))

        // check progressbar visible
        Espresso.onView(ViewMatchers.withId(R.id.progressbar_initial)).check(ViewAssertions.matches(
            ViewMatchers.isDisplayed()))

        // Initially "No internet connection will not be visible.
        Espresso.onView(ViewMatchers.withId(R.id.tv_no_internet)).check(ViewAssertions.matches(
            ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))

    }
}