package com.cts.telstrapoc.model.network

import retrofit2.Response
import java.io.IOException
import java.lang.Exception

abstract class APIExecutor {
    /**
     * It is a generic type function to execute api call
     * It takes function as parameter and return api response as generic type
     */
    suspend fun<T: Any> executeAPI(call: suspend () -> Response<T>) : T {
        try {
            val response = call.invoke()

            if (response.isSuccessful) {
                return response.body()!!
            } else {
                throw APIException(
                    response.code().toString()
                )
            }
        } catch (e: Exception){
            throw APIException(
                e.message.toString()
            )
        }
    }

    class APIException(message: String) : IOException(message)
}