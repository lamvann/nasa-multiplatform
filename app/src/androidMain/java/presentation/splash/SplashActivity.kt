package presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import data.NasaApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.UnstableDefault
import sample.R

@SuppressLint("Registered")
@ExperimentalStdlibApi
class SplashActivity : FragmentActivity() {

    @UnstableDefault
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = NasaApi()

        GlobalScope.launch {
            api.planetary { it ->
                GlobalScope.launch(Dispatchers.Main) {
                    findViewById<TextView>(R.id.main_text).text = "date: ${it.date}\n\nexplanation: ${it.explanation}"
                }
            }
        }
    }
}