package com.cts.telstrapoc.model.network

import com.cts.telstrapoc.model.CanadaAPIDetail
import com.cts.telstrapoc.model.CanadaAPIDetailInfo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ApiTest {

    private lateinit var api: Api

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        api = mockk()
    }

    @Test
    fun `GIVEN interface WHEN calling country detail api THEN CanadaAPIDetail response`() {
        // Given
        coEvery{ api.getCountryDetail()} returns getData()

        // When
        runBlocking { api.getCountryDetail() }

        // Then
        coVerify { api.getCountryDetail() }
    }

    fun getData() : Response<CanadaAPIDetail> {
        val title = "About Canada"
        val list : MutableList<CanadaAPIDetailInfo> = mutableListOf()
        list.add(CanadaAPIDetailInfo("", "", "Beavers"))
        return Response.success(CanadaAPIDetail(list, title))
    }

}

