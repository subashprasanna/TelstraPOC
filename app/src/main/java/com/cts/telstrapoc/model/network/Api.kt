package com.cts.telstrapoc.model.network

import com.cts.telstrapoc.model.CanadaAPIDetail
import com.cts.telstrapoc.util.CANADA_API_BASEURL
import com.cts.telstrapoc.util.CANADA_API_FUNCTION
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface Api {

    @GET(CANADA_API_FUNCTION)
    suspend fun getCountryDetail(): Response<CanadaAPIDetail>

    companion object {
        operator fun invoke() : Api {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(CANADA_API_BASEURL)
                .build()
                .create()
        }
    }
}