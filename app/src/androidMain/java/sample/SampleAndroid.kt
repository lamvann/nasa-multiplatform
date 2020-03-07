package sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import io.ktor.client.engine.android.Android

actual class Sample {
    actual fun checkMe() = 44
}

actual object Platform {
    actual val name: String = "Android"
}

//actual val httpEngine by lazy { Android.create() }

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Sample().checkMe()
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.main_text).text = hello()
    }
}