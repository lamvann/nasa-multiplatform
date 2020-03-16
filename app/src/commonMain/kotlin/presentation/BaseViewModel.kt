package presentation

import domain.usecase.BaseUseCase
import kotlinx.coroutines.CoroutineScope
import util.Failure

expect open class BaseViewModel() {
    val viewModelScope: CoroutineScope
    protected open fun onCleared() // must be have protected modifier, otherwise can't override. Why?

    fun <E : Any, P> launchUseCase(
        useCase: BaseUseCase<E, P>,
        params: P,
        onSuccess: (E) -> Unit
    )

    open fun onError(failure: Failure)
}