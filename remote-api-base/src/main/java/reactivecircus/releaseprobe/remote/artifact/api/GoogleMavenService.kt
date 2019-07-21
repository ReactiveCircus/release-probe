package reactivecircus.releaseprobe.remote.artifact.api

import reactivecircus.releaseprobe.remote.artifact.dto.ArtifactDTO
import reactivecircus.releaseprobe.remote.artifact.dto.ArtifactGroupDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface GoogleMavenService {

    @GET("master-index.xml")
    suspend fun fetchArtifactGroups(): List<ArtifactGroupDTO>

    @GET("{group_id}/group-index.xml")
    suspend fun fetchArtifactsByGroupId(
        @Path(value = "group_id", encoded = true) groupId: String
    ): List<ArtifactDTO>
}
