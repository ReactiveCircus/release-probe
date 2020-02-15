package reactivecircus.releaseprobe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import reactivecircus.releaseprobe.core.R as ResourcesR

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(ResourcesR.style.Theme_ReleaseProbe_DayNight)

        setContentView(R.layout.activity_main)
    }
}
