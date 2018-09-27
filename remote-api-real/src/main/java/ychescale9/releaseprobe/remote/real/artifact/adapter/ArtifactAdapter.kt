package ychescale9.releaseprobe.remote.real.artifact.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import java.io.IOException
import ychescale9.releaseprobe.remote.artifact.dto.ArtifactDTO

class ArtifactAdapter {

    @FromJson
    @Throws(IOException::class)
    fun fromJson(jsonReader: JsonReader): List<ArtifactDTO> {
        val artifactDTOs = mutableListOf<ArtifactDTO>()
        jsonReader.beginObject()
        val groupId = jsonReader.nextName()
        jsonReader.beginObject()
        while (jsonReader.hasNext()) {
            val artifactId = jsonReader.nextName()
            jsonReader.beginObject()
            jsonReader.skipName()
            val versions = jsonReader.nextString()
            artifactDTOs.add(ArtifactDTO(groupId, artifactId, versions))
            jsonReader.endObject()
        }
        jsonReader.endObject()
        jsonReader.endObject()

        return artifactDTOs
    }
}
