package presentation.splash

import data.response.PlanetaryResponse
import domain.GetPlanetaryData
import kotlinx.serialization.UnstableDefault
import presentation.BaseViewModel

class SplashViewModel : BaseViewModel() {

    private val planetaryData by lazy { GetPlanetaryData() }

    @UnstableDefault
    @ExperimentalStdlibApi
    fun getPlanetaryData(lambda: (PlanetaryResponse) -> Unit) {
        launchUseCase(planetaryData, Unit, lambda)
    }
}