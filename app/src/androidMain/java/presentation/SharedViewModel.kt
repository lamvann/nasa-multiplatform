package presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import domain.usecase.BaseUseCase
import androidx.lifecycle.viewModelScope as androidViewModelScope
import kotlinx.coroutines.CoroutineScope
import util.Failure
import util.onFailure
import util.onSuccess

actual open class SharedViewModel actual constructor(): ViewModel() {
    actual val viewModelScope: CoroutineScope = androidViewModelScope
    actual override fun onCleared() {
        super.onCleared()
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
        Log.d("ViewModel Failure", "Something bad happened $failure")
    }
}

abstract class BaseViewModel<UiStateType: Any> : SharedViewModel() {

}