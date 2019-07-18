package reactivecircus.releaseprobe.remote.real.artifact.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import java.io.IOException
import reactivecircus.releaseprobe.remote.artifact.dto.ArtifactGroupDTO

class ArtifactGroupAdapter {

    @FromJson
    @Throws(IOException::class)
    fun fromJson(jsonReader: JsonReader): List<ArtifactGroupDTO> {
        val artifactGroupDTOs = mutableListOf<ArtifactGroupDTO>()
        jsonReader.beginObject()
        jsonReader.skipName()
        jsonReader.beginObject()
        while (jsonReader.hasNext()) {
            val groupId = jsonReader.nextName()
            artifactGroupDTOs.add(ArtifactGroupDTO(groupId))
            jsonReader.nextString()
        }
        jsonReader.endObject()
        jsonReader.endObject()

        return artifactGroupDTOs
    }
}
