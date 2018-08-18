package ychescale9.releaseprobe.remote.artifact.adapter

import com.squareup.moshi.JsonReader
import okio.Buffer
import org.amshove.kluent.shouldEqual
import org.junit.Test

class ArtifactAdapterTest {

    private val artifactsJson = "{\"android.arch.lifecycle\":{" +
            "\"livedata\":{\"versions\":\"1.1.0,1.1.1\"}," +
            "\"viewmodel\":{\"versions\":\"1.1.0,1.1.1\"}," +
            "\"compiler\":{\"versions\":\"1.0.0-alpha1,1.0.0-alpha2,1.0.0-alpha3\"}" +
            "}}"

    private val adapter = ArtifactAdapter()

    @Test
    fun `should convert json to list of Artifacts`() {
        val reader = JsonReader.of(Buffer().writeUtf8(artifactsJson))
        adapter.fromJson(reader).sortedWith(compareBy { it.artifactId }).run {
            size shouldEqual 3
            get(0).groupId shouldEqual "android.arch.lifecycle"
            get(0).artifactId shouldEqual "compiler"
            get(0).versions shouldEqual "1.0.0-alpha1,1.0.0-alpha2,1.0.0-alpha3"

            get(1).groupId shouldEqual "android.arch.lifecycle"
            get(1).artifactId shouldEqual "livedata"
            get(1).versions shouldEqual "1.1.0,1.1.1"

            get(2).groupId shouldEqual "android.arch.lifecycle"
            get(2).artifactId shouldEqual "viewmodel"
            get(2).versions shouldEqual "1.1.0,1.1.1"
        }
    }
}
