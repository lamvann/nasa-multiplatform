package presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import domain.BaseUseCase
import androidx.lifecycle.viewModelScope as androidViewModelScope
import kotlinx.coroutines.CoroutineScope
import util.Failure
import util.onFailure
import util.onSuccess

actual open class BaseViewModel actual constructor(): ViewModel() {
    actual val viewModelScope: CoroutineScope = androidViewModelScope
    actual override fun onCleared() {
        super.onCleared()
    }

    actual fun <E : Any, P> launchUseCase(
        useCase: BaseUseCase<E, P>,
        params: P,
        onSuccess: (E) -> Unit
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