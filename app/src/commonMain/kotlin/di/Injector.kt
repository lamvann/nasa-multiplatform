package di

import data.httpEngine
import data.respository.PicOfTheDayRepositoryImpl
import data.source.NasaApi
import domain.repository.PicOfTheDayRepository
import domain.usecase.GetPicOfTheDay
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.UnstableDefault
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.provider
import org.kodein.di.erased.singleton
import kotlin.native.concurrent.ThreadLocal

@UnstableDefault
@ThreadLocal
val injector = Kodein {

    // data
    bind<HttpClient>() with singleton {
        HttpClient(httpEngine) {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }
    bind<NasaApi>() with singleton { NasaApi() }
    bind<PicOfTheDayRepository>() with provider { PicOfTheDayRepositoryImpl() }

    // domain
    bind<GetPicOfTheDay>() with singleton { GetPicOfTheDay(instance()) }
}