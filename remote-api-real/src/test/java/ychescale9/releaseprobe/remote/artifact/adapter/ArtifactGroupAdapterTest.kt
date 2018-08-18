package ychescale9.releaseprobe.remote.artifact.adapter

import com.squareup.moshi.JsonReader
import okio.Buffer
import org.amshove.kluent.shouldEqual
import org.junit.Test

class ArtifactGroupAdapterTest {

    private val artifactGroupsJson = "{\"metadata\":" +
            "{\"androidx.core\":\"\"," +
            "\"androidx.test\":\"\"," +
            "\"androidx.room\":\"\"}" +
            "}"

    private val adapter = ArtifactGroupAdapter()

    @Test
    fun `should convert json to list of ArtifactGroups`() {
        val reader = JsonReader.of(Buffer().writeUtf8(artifactGroupsJson))
        adapter.fromJson(reader).sortedWith(compareBy {it.groupId}).run {
            size shouldEqual 3
            get(0).groupId shouldEqual "androidx.core"
            get(1).groupId shouldEqual "androidx.room"
            get(2).groupId shouldEqual "androidx.test"
        }
    }
}
