package sample

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.http.URLProtocol.Companion.HTTPS
import io.ktor.http.Url
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json

@Serializable
data class Item(val date: String, val explanation: String)

@ExperimentalStdlibApi
class NasaApi {
    private val client = HttpClient(httpEngine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    @OptIn(UnstableDefault::class)
    fun info(callback: (String) -> Unit) {
        GlobalScope.launch {
            val rawResponse = client.get<HttpResponse> {
                url(Url("https://api.nasa.gov/planetary/apod?api_key=9kPuiHvzHiIHbrc13fbGnOdkoGk2aphtwmHbyjLC"))
//                url {
//                    protocol = HTTPS
//                    host = "api.nasa.gov"
//                    encodedPath = "planetary/apod"
//                    parameter("api_key", "9kPuiHvzHiIHbrc13fbGnOdkoGk2aphtwmHbyjLC")
//                }
            }
//            val parsedResult = Json.parse(Item.serializer(), rawResponse.readText())
            callback(rawResponse.readText())
        }
    }
}