package data

import io.ktor.client.engine.ios.Ios

actual val httpEngine by lazy { Ios.create() }