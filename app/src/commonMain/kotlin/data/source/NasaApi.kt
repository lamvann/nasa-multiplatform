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
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json

@UnstableDefault
class NasaApi {

    // HTTP client could be injected
    private val client = HttpClient(httpEngine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun planetary(): PlanetaryResponse {
        val rawResponse = client.get<HttpResponse> {
            url {
                protocol = HTTPS
                host = "api.nasa.gov"
                encodedPath = "planetary/apod"
                parameter("api_key", "9kPuiHvzHiIHbrc13fbGnOdkoGk2aphtwmHbyjLC")
            }
        }
        return Json.parse(PlanetaryResponse.serializer(), rawResponse.readText())
    }
}