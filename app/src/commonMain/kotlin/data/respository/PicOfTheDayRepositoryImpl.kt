package data.respository

import data.mapper.PicOfTheDayMapper
import data.source.NasaApi
import domain.repository.PicOfTheDayRepository
import kotlinx.serialization.UnstableDefault

class PicOfTheDayRepositoryImpl : PicOfTheDayRepository {

    // TODO eventually will inject
    @UnstableDefault
    private val nasaDataSource by lazy { NasaApi() }
    private val mapper by lazy { PicOfTheDayMapper() }

    // TODO some network error handling here would be nice
    @UnstableDefault
    override suspend fun fetchPlanetaryData(date: String) =
        mapper(nasaDataSource.picOfTheDay(date))
}