package com.cts.telstrapoc.viewmodel

import com.cts.telstrapoc.ApiResponse
import com.cts.telstrapoc.model.CanadaAPIDetail
import com.cts.telstrapoc.model.CanadaAPIDetailInfo
import com.cts.telstrapoc.model.repository.CountryRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

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
