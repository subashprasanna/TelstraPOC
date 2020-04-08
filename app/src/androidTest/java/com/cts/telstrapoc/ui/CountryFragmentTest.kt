package com.cts.telstrapoc.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
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
        ActivityScenario.launch(MainActivity::class.java)

        // check host fragment got loaded on screen
        Espresso.onView(ViewMatchers.withId(R.id.parent_layout)).check(ViewAssertions.matches(
            ViewMatchers.isDisplayed()))

    }
}