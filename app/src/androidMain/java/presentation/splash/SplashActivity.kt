package presentation.splash

import android.os.Bundle
import kotlinx.serialization.UnstableDefault
import presentation.BaseFragment
import presentation.bindingadapter.imageUrl
import sample.R
import sample.databinding.FragmentSplashBinding

@UnstableDefault
class SplashActivity : BaseFragment<SplashViewModel, FragmentSplashBinding>(R.layout.fragment_splash) {
    // TODO figure out how to observe data from ViewModel without [LiveData]
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getPicOfTheDay {
            binding.ivBackground.imageUrl(it.hdurl)
            binding.mainText.text = it.title
        }
    }
}