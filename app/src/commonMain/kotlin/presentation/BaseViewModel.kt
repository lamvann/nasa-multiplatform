package presentation

import kotlinx.coroutines.CoroutineScope

expect open class BaseViewModel() {
    val viewModelScope: CoroutineScope
    protected open fun onCleared() // must be have protected modifier, otherwise can override. Why?
}