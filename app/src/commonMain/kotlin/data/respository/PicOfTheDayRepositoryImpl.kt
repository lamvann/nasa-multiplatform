package data.respository

import data.mapper.PlanetaryMapper
import data.source.NasaApi
import domain.repository.PlanetaryRepository
import kotlinx.serialization.UnstableDefault

class PlanetaryRepositoryImpl : PlanetaryRepository {

    // TODO eventually will inject
    @UnstableDefault
    private val nasaDataSource by lazy { NasaApi() }
    private val mapper by lazy { PlanetaryMapper() }

    // TODO some network error handling here would be nice
    @UnstableDefault
    override suspend fun fetchPlanetaryData() = mapper(nasaDataSource.picOfTheDay())
}