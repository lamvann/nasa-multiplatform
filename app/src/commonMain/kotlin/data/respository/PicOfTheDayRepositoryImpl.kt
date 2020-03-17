package data.respository

import data.mapper.PicOfTheDayMapper
import data.source.NasaApi
import di.injector
import domain.entity.PicOfTheDay
import domain.repository.PicOfTheDayRepository
import kotlinx.serialization.UnstableDefault
import org.kodein.di.erased.instance

class PicOfTheDayRepositoryImpl : PicOfTheDayRepository {

    @UnstableDefault
    private val nasaDataSource: NasaApi by injector.instance()
    private val mapper = PicOfTheDayMapper()

    // TODO some network error handling here would be nice
    @UnstableDefault
    override suspend fun fetchPlanetaryData(date: String): PicOfTheDay {
        return mapper(nasaDataSource.picOfTheDay(date))
    }
}