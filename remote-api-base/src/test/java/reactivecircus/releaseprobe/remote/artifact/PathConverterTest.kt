package reactivecircus.releaseprobe.remote.artifact

import org.amshove.kluent.shouldEqual
import org.junit.Test

class PathConverterTest {

    @Test
    fun `convert artifact group ID to url path`() {
        "androidx.test.espresso".toPath() shouldEqual "androidx/test/espresso"
    }
}
