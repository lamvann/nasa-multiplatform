package domain

import data.NasaApi
import data.response.PlanetaryResponse
import kotlinx.serialization.UnstableDefault
import util.Either
import util.Failure

class GetPlanetaryData : BaseUseCase<PlanetaryResponse, Unit>() {
    @UnstableDefault
    override suspend fun run(params: Unit): Either<Failure, PlanetaryResponse> {
        return Either.Right(NasaApi().planetary())
    }
}