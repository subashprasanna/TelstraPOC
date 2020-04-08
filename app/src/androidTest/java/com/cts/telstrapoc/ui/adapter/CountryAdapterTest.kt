package com.cts.telstrapoc.ui.adapter

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.cts.telstrapoc.model.CanadaAPIDetailInfo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class CountryAdapterTest {
    private lateinit var countryAdapter: CountryAdapter

    val one = CanadaAPIDetailInfo("one", "one", "one")
    val two = CanadaAPIDetailInfo("two", "two", "two")
    val three = CanadaAPIDetailInfo("three", "three", "three")

    @Before
    fun setUp() {
        countryAdapter = CountryAdapter(listOf<CanadaAPIDetailInfo>(one, two, three))
    }

    @Test
    fun checkItemCount() {
        assertTrue(countryAdapter.getItemCount() == 3)
    }

    @Test
    fun checkItemAtPosition() {
        val sample = CanadaAPIDetailInfo("one", "one", "one")
        assertEquals(sample, one)
        assertNotEquals(sample, two)
    }
}