package com.cts.telstrapoc.di

import com.cts.telstrapoc.viewmodel.CountryViewModelFactory
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class CountryModuleTest {
    @RelaxedMockK
    private lateinit var countryModule: CountryModule

    @RelaxedMockK
    private lateinit var factory: CountryViewModelFactory

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun checkCoundryModuleInjectedProperly() {
        val viewModelFactory = countryModule.bindCountryViewModelFactory(factory)
        assertNotNull(viewModelFactory)
    }

}