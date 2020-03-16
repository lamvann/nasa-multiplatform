package domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import util.Either
import util.Failure

abstract class BaseUseCase<out Entity: Any, in Params> {
    abstract suspend fun run(params: Params): Either<Failure, Entity>

    operator fun invoke(
        scope: CoroutineScope,
        params: Params,
        onResult: (Either<Failure, Entity>) -> Unit = {}
    ) {
        val background = scope.async { run(params) }
        scope.launch { onResult(background.await()) }
    }
}