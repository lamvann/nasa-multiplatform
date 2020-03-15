package presentation.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.serialization.UnstableDefault
import presentation.BaseFragment
import sample.R

@ExperimentalStdlibApi
class SplashActivity : BaseFragment<SplashViewModel>(R.layout.activity_splash) {

    @SuppressLint("SetTextI18n")
    @UnstableDefault
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getPlanetaryData {
            findViewById<TextView>(R.id.main_text).text = "date: ${it.date}\n\nexplanation: ${it.explanation}"
        }
    }
}