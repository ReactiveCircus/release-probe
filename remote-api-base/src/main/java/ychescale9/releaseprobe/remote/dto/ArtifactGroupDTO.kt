package ychescale9.releaseprobe.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ArtifactGroupDTO(
    @Json(name = "groupId")
    val groupId: String
)
