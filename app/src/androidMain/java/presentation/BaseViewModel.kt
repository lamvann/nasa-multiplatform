package presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope as androidViewModelScope
import kotlinx.coroutines.CoroutineScope

actual open class BaseViewModel actual constructor(): ViewModel() {
    actual val viewModelScope: CoroutineScope = androidViewModelScope
    actual override fun onCleared() {
        super.onCleared()
    }
}