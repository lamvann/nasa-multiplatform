package presentation.splash

import data.NasaApi
import data.response.PlanetaryResponse
import kotlinx.coroutines.launch
import kotlinx.serialization.UnstableDefault
import presentation.BaseViewModel

class SplashViewModel : BaseViewModel() {
    @UnstableDefault
    @ExperimentalStdlibApi
    fun getPlanetaryData(lambda: (PlanetaryResponse) -> Unit) {
        viewModelScope.launch {
            NasaApi().planetary(lambda) // Uses API directly but not for long, more architecture coming soon
        }
    }
}