package presentation

import domain.usecase.BaseUseCase
import kotlinx.coroutines.*
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import util.Failure
import util.onFailure
import util.onSuccess
import kotlin.coroutines.CoroutineContext

actual open class SharedViewModel actual constructor() {
    private val viewModelJob = SupervisorJob()
    private val coroutineScope: CoroutineScope = CoroutineScope(IosMainDispatcher + viewModelJob)

    actual val viewModelScope: CoroutineScope = coroutineScope

    protected actual open fun onCleared() {
        viewModelJob.cancelChildren()
    }

    actual fun <Entity : Any, Params> launchUseCase(
        useCase: BaseUseCase<Entity, Params>,
        params: Params,
        onSuccess: (Entity) -> Unit
    ) {
        useCase.invoke(viewModelScope, params) { either ->
            either
                .onSuccess(onSuccess)
                .onFailure(::onError)
        }
    }

    actual open fun onError(failure: Failure) {
        println("Something bad happened $failure")

    }

    private object IosMainDispatcher : CoroutineDispatcher() {
        override fun dispatch(context: CoroutineContext, block: Runnable) {
            dispatch_async(dispatch_get_main_queue()) { block.run() }
        }
    }
}