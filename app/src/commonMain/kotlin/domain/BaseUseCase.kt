package domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import util.Either
import util.Failure

abstract class BaseUseCase<out Success: Any, in Params> {
    abstract suspend fun run(params: Params): Either<Failure, Success>

    suspend operator fun invoke(
        scope: CoroutineScope,
        params: Params,
        onResult: (Either<Failure, Success>) -> Unit = {}
    ) {
        val background = scope.async { run(params) }
        scope.launch { onResult(background.await()) }
    }
}