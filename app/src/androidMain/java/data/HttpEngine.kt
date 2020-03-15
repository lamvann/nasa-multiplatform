package data

import io.ktor.client.engine.android.Android

actual val httpEngine by lazy { Android.create() }