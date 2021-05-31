package com.github.pagr0m.shared

actual class Platform actual constructor() {
    actual val platform: String
        get() = "Hello iOS from Kotlin"
}
