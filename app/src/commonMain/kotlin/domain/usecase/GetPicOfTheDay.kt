package domain.usecase

import domain.entity.PicOfTheDay
import domain.repository.PicOfTheDayRepository
import kotlinx.serialization.UnstableDefault
import util.Either
import util.Failure

class GetPicOfTheDay(
    private val repository: PicOfTheDayRepository
) : BaseUseCase<PicOfTheDay, Unit>() {

    @UnstableDefault
    override suspend fun run(params: Unit): Either<Failure, PicOfTheDay> {
        // TODO Create a 'TimeRepository' class and inject to UseCase
        return Either.Right(repository.fetchPlanetaryData("2020-03-07"))
    }
}