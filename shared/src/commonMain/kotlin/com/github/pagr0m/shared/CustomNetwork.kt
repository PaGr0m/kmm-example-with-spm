package com.github.pagr0m.shared

expect class CustomNetwork() {
    fun get(url: String): String
}
