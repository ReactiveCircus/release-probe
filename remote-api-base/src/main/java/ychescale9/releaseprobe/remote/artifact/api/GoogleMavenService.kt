package ychescale9.releaseprobe.remote.artifact.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ychescale9.releaseprobe.remote.artifact.dto.ArtifactDTO
import ychescale9.releaseprobe.remote.artifact.dto.ArtifactGroupDTO

interface GoogleMavenService {

    @GET("master-index.xml")
    fun fetchArtifactGroups(): Single<List<ArtifactGroupDTO>>

    @GET("{group_id}/group-index.xml")
    fun fetchArtifactsByGroupId(@Path(value = "group_id", encoded = true) groupId: String): Single<List<ArtifactDTO>>
}
