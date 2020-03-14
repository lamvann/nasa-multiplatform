package sample

import io.ktor.client.engine.HttpClientEngine

expect class Sample() {
    fun checkMe(): Int
}

expect object Platform {
    val name: String
}

fun hello(): String = "Hello from ${Platform.name}"


expect val httpEngine: HttpClientEngine

class Proxy {
    fun proxyHello() = hello()
}
