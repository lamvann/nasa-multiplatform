package sample

import io.ktor.client.engine.ios.Ios

actual class Sample {
    actual fun checkMe() = 7
}

actual object Platform {
    actual val name: String = "iOS"
}

actual val httpEngine by lazy { Ios.create() }