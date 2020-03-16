package presentation.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.serialization.UnstableDefault
import presentation.BaseFragment
import sample.R

@ExperimentalStdlibApi
class SplashActivity : BaseFragment<SplashViewModel>(R.layout.fragment_splash) {

    @SuppressLint("SetTextI18n")
    @UnstableDefault
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getPlanetaryData {
            val backgroundImage = findViewById<ImageView>(R.id.iv_background)
            Glide.with(this).load(it.hdurl).into(backgroundImage)

            findViewById<TextView>(R.id.main_text).text = it.title
        }
    }
}