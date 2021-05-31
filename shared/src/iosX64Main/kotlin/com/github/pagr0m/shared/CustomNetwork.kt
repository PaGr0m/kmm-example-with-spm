package com.github.pagr0m.shared

import platform.Foundation.NSError
import platform.Foundation.NSProgress
import platform.Foundation.NSURLSessionDataTask
import spm.AFNetworking.AFHTTPSessionManager

actual class CustomNetwork actual constructor() {
    actual fun get(url: String): String {
        val manager = AFHTTPSessionManager()

        manager.GET(
            url,
            parameters = null,
            headers = null,
            progress = { nsProgress: NSProgress? ->
                println("progress: ${nsProgress.toString()}")
            },
            success = { nsurlSessionDataTask: NSURLSessionDataTask?, any: Any? ->
                println("success: ${nsurlSessionDataTask.toString()}")
                println("success: ${any.toString()}")
            },
            failure = { nsurlSessionDataTask: NSURLSessionDataTask?, nsError: NSError? ->
                println("failure: ${nsurlSessionDataTask.toString()}")
                println("failure: ${nsError.toString()}")
            }
        )

        return "null"
    }
}
