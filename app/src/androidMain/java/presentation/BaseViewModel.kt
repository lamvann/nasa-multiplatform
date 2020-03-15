package presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

actual open class BaseViewModel actual constructor(): ViewModel() {
    actual val coroutineScope: CoroutineScope = viewModelScope
    actual override fun onCleared() {
        super.onCleared()
    }
}