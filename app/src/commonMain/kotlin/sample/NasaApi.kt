package sample

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.http.URLProtocol.Companion.HTTPS
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json

@Serializable
data class Item(
    val date: String,
    val explanation: String,
    @SerialName("media_type") val mediaType: String,
    @SerialName("service_version") val serviceVersion: String,
    val title: String,
    val url: String
)

@UnstableDefault
@ExperimentalStdlibApi
class NasaApi {
    private val client = HttpClient(httpEngine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun info(callback: (Item) -> Unit) {
        val rawResponse = client.get<HttpResponse> {
            url {
                protocol = HTTPS
                host = "api.nasa.gov"
                encodedPath = "planetary/apod"
                parameter("api_key", "9kPuiHvzHiIHbrc13fbGnOdkoGk2aphtwmHbyjLC")
            }
        }
        val parsedResult = Json.parse(Item.serializer(), rawResponse.readText())
        callback(parsedResult)
    }
}