package com.github.pagr0m.shared

import com.squareup.okhttp.*
import java.io.IOException
import java.util.concurrent.CountDownLatch

actual class CustomNetwork {
    actual fun get(url: String): Any {
        val client = OkHttpClient()

        val countDownLatch = CountDownLatch(1)
        var result = "null"
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(request: Request?, e: IOException) {
                result = e.message.toString()
                countDownLatch.countDown()
            }

            override fun onResponse(response: Response) {
                result = response.body().string()
                countDownLatch.countDown()
            }
        })

        countDownLatch.await()

        return result
    }
}
