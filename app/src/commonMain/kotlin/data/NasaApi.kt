package data

import data.response.PlanetaryResponse
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

@UnstableDefault
@ExperimentalStdlibApi
class NasaApi {
    private val client = HttpClient(httpEngine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun planetary(callback: (PlanetaryResponse) -> Unit) {
        val rawResponse = client.get<HttpResponse> {
            url {
                protocol = HTTPS
                host = "api.nasa.gov"
                encodedPath = "planetary/apod"
                parameter("api_key", "9kPuiHvzHiIHbrc13fbGnOdkoGk2aphtwmHbyjLC")
            }
        }
        val parsedResult = Json.parse(PlanetaryResponse.serializer(), rawResponse.readText())
        callback(parsedResult)
    }
}