package reactivecircus.releaseprobe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_ReleaseProbe_DayNight)

        setContentView(R.layout.activity_main)
    }
}
