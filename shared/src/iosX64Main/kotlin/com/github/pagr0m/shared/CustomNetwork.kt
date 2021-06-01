package com.github.pagr0m.shared

import platform.Foundation.NSError
import platform.Foundation.NSURLSessionDataTask
import platform.darwin.*
import spm.AFNetworking.AFHTTPSessionManager
import spm.AFNetworking.AFJSONResponseSerializer

actual class CustomNetwork actual constructor() {
    actual fun get(url: String): Any {
        var response : Any = ""

        val semaphore = dispatch_semaphore_create(0)
        val queue = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT.toLong(), 0);
        val jsonSerializer = AFJSONResponseSerializer()

        val manager = AFHTTPSessionManager()
        manager.completionQueue = queue
        manager.responseSerializer = jsonSerializer

        val sessionDataTask = manager.GET(
            url,
            parameters = null,
            headers = null,
            progress = null,
            success = { _: NSURLSessionDataTask?, responseObject: Any? ->
                println(responseObject)
                response = responseObject!!
                dispatch_semaphore_signal(semaphore)
            },
            failure = { _: NSURLSessionDataTask?, error: NSError? ->
                println(error.toString())
                response = error.toString()
                dispatch_semaphore_signal(semaphore)
            }
        )

        dispatch_semaphore_wait(semaphore, DISPATCH_TIME_FOREVER);

        return response
    }
}
