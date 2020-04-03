package com.cts.telstrapoc.model.repository

import com.cts.telstrapoc.model.network.Api
import com.cts.telstrapoc.model.network.APIExecutor
import javax.inject.Inject

class CountryRepository @Inject constructor(
    private val api: Api
): APIExecutor(){
    suspend fun getCountryDetail() = executeAPI {
        api.getCountryDetail()
    }
}