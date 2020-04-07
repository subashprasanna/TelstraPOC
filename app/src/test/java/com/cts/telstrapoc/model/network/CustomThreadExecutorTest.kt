package com.cts.telstrapoc.model.network

import com.cts.telstrapoc.ApiResponse
import com.cts.telstrapoc.model.repository.CountryRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CustomThreadExecutorTest {

    private lateinit var executor: CustomThreadExecutor
    private lateinit var repository: CountryRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        executor = mockk()
        repository = mockk()
    }

    @Test
    fun `GIVEN  WHEN  THEN`() {
        //
        //coEvery(executor.runApiOnIOThreadthenMainThread(suspend {  repository.getCountryDetail() }, {ApiResponse.getSampleResponse()}))
        //runBlocking { executor.runApiOnIOThreadthenMainThread(suspend {  repository.getCountryDetail() }, {ApiResponse.getSampleResponse()}) }
    }
}