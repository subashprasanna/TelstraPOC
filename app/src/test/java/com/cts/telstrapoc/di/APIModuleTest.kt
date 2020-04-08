package com.cts.telstrapoc.di

import com.cts.telstrapoc.model.network.Api
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class APIModuleTest {
    @RelaxedMockK
    private lateinit var apiModule: APIModule

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun checkAPIModuleInjectedProperly() {
        val api: Api = apiModule.providesAPI()
        assertNotNull(api)
    }
}