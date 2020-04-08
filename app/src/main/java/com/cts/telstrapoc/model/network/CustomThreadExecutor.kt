package com.cts.telstrapoc.model.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

object CustomThreadExecutor {
    /**
     * Let -> IO Thread handled background task (Retrofit server call) and
     * Main Thread handle server response for UI
     *
     * lamda expression is used for argument
     *
     * work arg - suspend function does not take any args returns nullable generic type
     *            after executing work suspending function callback will be called
     * callback arg - sends the response back to a class where api called
     *
     */
    fun <T: Any> runApiOnIOThreadthenMainThread(
        work: suspend (() -> T?),
        callback: ((T?) -> Unit)
    ) = CoroutineScope(Dispatchers.Main).launch {
            val data = CoroutineScope(Dispatchers.IO).async rt@{
                return@rt work()
            }.await()

            callback(data)
        }
}