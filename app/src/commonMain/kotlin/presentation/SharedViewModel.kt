package presentation

import domain.usecase.BaseUseCase
import kotlinx.coroutines.CoroutineScope
import util.Failure

expect open class SharedViewModel() {
    val viewModelScope: CoroutineScope
    protected open fun onCleared() // must be have protected modifier, otherwise can't override. Why?

    fun <Entity : Any, Params> launchUseCase(
        useCase: BaseUseCase<Entity, Params>,
        params: Params,
        onSuccess: (Entity) -> Unit
    )

    open fun onError(failure: Failure)
}