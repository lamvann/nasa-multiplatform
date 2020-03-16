package domain.usecase

import data.respository.PicOfTheDayRepositoryImpl
import domain.entity.PicOfTheDay
import kotlinx.serialization.UnstableDefault
import util.Either
import util.Failure

class GetPlanetaryData : BaseUseCase<PicOfTheDay, Unit>() {
    private val repository by lazy { PicOfTheDayRepositoryImpl() }

    @UnstableDefault
    override suspend fun run(params: Unit): Either<Failure, PicOfTheDay> {
        // check if today's response is an image
//        val result = repository.fetchPlanetaryData()
//
//        return when (result.mediaType) {
//            MediaType.Image -> Either.Right(result)
//            else -> {
//
//            }
//        }

        return Either.Right(repository.fetchPlanetaryData("2020-03-14"))
    }

    // TODO Create a 'TimeRepository' class and inject to UseCase
}