package data.source

import data.httpEngine
import data.response.PicOfTheDayResponse
import di.injector
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
import org.kodein.di.erased.instance

@UnstableDefault
class NasaApi {

    private val client: HttpClient by injector.instance()

    suspend fun picOfTheDay(date: String = ""): PicOfTheDayResponse {
        val rawResponse = client.get<HttpResponse> {
            url {
                protocol = HTTPS
                host = "api.nasa.gov"
                encodedPath = "planetary/apod"
                parameter("api_key", "9kPuiHvzHiIHbrc13fbGnOdkoGk2aphtwmHbyjLC")
                parameter("date", date)
                parameter("hd", true)
            }
        }
        return Json.parse(PicOfTheDayResponse.serializer(), rawResponse.readText())
    }
}