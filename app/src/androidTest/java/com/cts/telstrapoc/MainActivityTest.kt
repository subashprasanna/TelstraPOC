package com.cts.telstrapoc

import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    @Test
    @Throws(Exception::class)
    fun loadHostFragment() {
        var navigator = (activityRule.activity.supportFragmentManager.primaryNavigationFragment as NavHostFragment)
            .navController.navigatorProvider.getNavigator(FragmentNavigator::class.java)
    }
}