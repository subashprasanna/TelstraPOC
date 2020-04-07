package com.cts.telstrapoc.model.repository

import com.cts.telstrapoc.model.network.Api
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class CountryRepositoryTest {

    private lateinit var api: Api
    private lateinit var repository: CountryRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        api = mockk()
        repository = CountryRepository(api)
    }

    @Test
    fun `GIVEN  WHEN  THEN`() {
        //coEvery( repository.getCountryDetail())
        //val result = runBlocking { repository.getCountryDetail() }
    }

}