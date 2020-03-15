package presentation.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.serialization.UnstableDefault
import sample.R

@SuppressLint("Registered")
@ExperimentalStdlibApi
class SplashActivity : FragmentActivity() {

    private val splashViewModel: SplashViewModel by lazy {
        ViewModelProvider(this)[SplashViewModel::class.java]
    }

    @SuppressLint("SetTextI18n")
    @UnstableDefault
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashViewModel.getPlanetaryData {
            findViewById<TextView>(R.id.main_text).text = "date: ${it.date}\n\nexplanation: ${it.explanation}"
        }
    }
}