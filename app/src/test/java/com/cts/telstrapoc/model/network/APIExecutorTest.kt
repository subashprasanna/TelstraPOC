package com.cts.telstrapoc.model.network

import com.cts.telstrapoc.ApiResponse
import com.cts.telstrapoc.model.CanadaAPIDetail
import com.cts.telstrapoc.model.CanadaAPIDetailInfo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class APIExecutorTest {
    private lateinit var api: Api
    private lateinit var apiExecutor: APIExecutor

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        apiExecutor = mockk()
        api = mockk<Api>()
    }

    @Test
    fun `GIVEN api executer WHEN executer call api THEN api response returned`() {
        // Given
        val sampleMock = mockk<suspend () -> Response<CanadaAPIDetail>>()
        coEvery{ apiExecutor.executeAPI(sampleMock)}.answers { ApiResponse.getSampleResponse() }

        // When
        val response  = runBlocking {
            apiExecutor.executeAPI(sampleMock)
        }

        // Then
        coVerify { apiExecutor.executeAPI(sampleMock) }
    }
}