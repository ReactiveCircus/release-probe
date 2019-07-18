package reactivecircus.releaseprobe

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class MainTestRunner : AndroidJUnitRunner() {

    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, MainTestApp::class.java.name, context)
    }
}
