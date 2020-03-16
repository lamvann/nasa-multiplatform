package presentation.splash

import domain.entity.PicOfTheDay
import domain.usecase.GetPlanetaryData
import kotlinx.serialization.UnstableDefault
import presentation.BaseViewModel

class SplashViewModel : BaseViewModel() {

    private val planetaryData by lazy { GetPlanetaryData() }

    @UnstableDefault
    @ExperimentalStdlibApi
    fun getPlanetaryData(lambda: (PicOfTheDay) -> Unit) {
        launchUseCase(planetaryData, Unit, lambda)
    }
}