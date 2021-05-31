package com.github.pagr0m.shared

import platform.Foundation.NSError
import platform.Foundation.NSURLSessionDataTask
import spm.AFNetworking.AFHTTPSessionManager

actual class CustomNetwork actual constructor() {
    actual fun get(url: String): String {
        var response = "empty"
        val manager = AFHTTPSessionManager()

        val sessionDataTask = manager.GET(
            url,
            parameters = null,
            headers = null,
            progress = null,
            success = { _: NSURLSessionDataTask?, responseObject: Any? ->
                response = responseObject.toString()
                println(responseObject.toString())
            },
            failure = { _: NSURLSessionDataTask?, error: NSError? ->
                println(error.toString())
            }
        )

        println("Check0: " + response)

        println("Check1: " + sessionDataTask?.response.toString())
        println("Check2: " + sessionDataTask?.currentRequest().toString())
        println("Check3: " + sessionDataTask?.description)
        println("Check4: " + sessionDataTask?.response())
        response = sessionDataTask?.response.toString()

        return response
    }
}
