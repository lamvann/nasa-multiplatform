package presentation.splash

import di.injector
import domain.entity.PicOfTheDay
import domain.usecase.GetPicOfTheDay
import kotlinx.serialization.UnstableDefault
import org.kodein.di.erased.instance
import presentation.BaseViewModel

@UnstableDefault
class SplashViewModel : BaseViewModel() {
    private val planetaryData: GetPicOfTheDay by injector.instance()

    fun getPicOfTheDay(lambda: (PicOfTheDay) -> Unit) =
            launchUseCase(planetaryData, Unit, lambda)
}

