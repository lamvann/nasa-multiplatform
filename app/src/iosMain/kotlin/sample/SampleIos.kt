package sample

import io.ktor.client.engine.ios.Ios
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.UnstableDefault

actual class Sample {
    actual fun checkMe() = 7
}

actual object Platform {
    actual val name: String = "iOS"
}

@UnstableDefault
@ExperimentalStdlibApi
fun getNasaData(predicate: (Item) -> Unit) {
    MainScope().launch {
        NasaApi().info {
            predicate(it)
        }
    }
}

@UnstableDefault
@ExperimentalStdlibApi
fun NasaApi.get(predicate: (Item) -> Unit) {
    MainScope().launch {
        NasaApi().info(predicate)
    }
}

actual val httpEngine by lazy { Ios.create() }