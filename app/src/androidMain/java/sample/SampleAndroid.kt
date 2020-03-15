package sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import io.ktor.client.engine.android.Android
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.serialization.UnstableDefault
import kotlin.coroutines.CoroutineContext

actual class Sample {
    actual fun checkMe() = 44
}

actual object Platform {
    actual val name: String = "Android"
}

actual val httpEngine by lazy { Android.create() }

@ExperimentalStdlibApi
class MainActivity : AppCompatActivity() {

    @UnstableDefault
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Sample().checkMe()
        setContentView(R.layout.activity_main)

        val api = NasaApi()

        GlobalScope.launch {
            api.info { it ->
                GlobalScope.launch(Dispatchers.Main) {
                    findViewById<TextView>(R.id.main_text).text = "date: ${it.date}\n\nexplanation: ${it.explanation}"
                }
            }
        }
    }
}