package com.cts.telstrapoc.viewmodel

import com.cts.telstrapoc.ApiResponse
import com.cts.telstrapoc.model.repository.CountryRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class CountryViewModelTest {
    @RelaxedMockK
    private lateinit var repository: CountryRepository

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `GIVEN viewmodel WHEN canada detail api request THEN country Detail field should have values`() {
        runBlocking {
            launch(Dispatchers.Main) {
                // Given
                val viewmodel = CountryViewModel(repository)

                // WHEN
                val result = viewmodel.getCanadaInfo()

                // Then
                assertNotNull(result)
            }
        }
    }

    @Test
    fun `GIVEN viewmodel WHEN canada detail api response loaded THEN avoid empty records`() {
        runBlocking {
            launch(Dispatchers.Main) {
                // Given
                val viewmodel = CountryViewModel(repository)
                val originalList = ApiResponse.getSampleResponse()

                // WHEN
                val listWithoutEmptyRecord = viewmodel.removeEmptyCountryInformation(ApiResponse.getSampleResponse())


                // Then
                assertTrue(listWithoutEmptyRecord.rows.size < originalList.rows.size)

            }
        }
    }
}
