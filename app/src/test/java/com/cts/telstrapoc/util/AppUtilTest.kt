package com.cts.telstrapoc.util

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppUtilTest {
    @Test
    fun testURLReachable() {
        // Disable wifi and check for fail condition
        val isURLReachable = AppUtil.checkURLReachable()
        Assert.assertTrue(isURLReachable)
    }

    @Test
    fun testOnline() {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val isOnline = AppUtil.isOnline(context)
        Assert.assertTrue(isOnline)
    }
}