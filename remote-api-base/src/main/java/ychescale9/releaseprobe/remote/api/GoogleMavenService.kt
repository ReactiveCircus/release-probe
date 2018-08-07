package ychescale9.releaseprobe.remote.api

import io.reactivex.Single
import retrofit2.http.GET
import ychescale9.releaseprobe.remote.dto.ArtifactGroupDTO

interface GoogleMavenService {

    @GET("master-index.xml")
    fun fetchArtifactGroups(): Single<List<ArtifactGroupDTO>>
}
