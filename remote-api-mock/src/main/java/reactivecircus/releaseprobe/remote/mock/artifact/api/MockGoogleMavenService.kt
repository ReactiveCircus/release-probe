package reactivecircus.releaseprobe.remote.mock.artifact.api

import io.reactivex.Single
import retrofit2.mock.BehaviorDelegate
import reactivecircus.releaseprobe.remote.FakeGoogleMavenData
import reactivecircus.releaseprobe.remote.artifact.api.GoogleMavenService
import reactivecircus.releaseprobe.remote.artifact.dto.ArtifactDTO
import reactivecircus.releaseprobe.remote.artifact.dto.ArtifactGroupDTO

class MockGoogleMavenService(private val delegate: BehaviorDelegate<GoogleMavenService>) : GoogleMavenService {

    override fun fetchArtifactGroups(): Single<List<ArtifactGroupDTO>> {
        return delegate.returningResponse(FakeGoogleMavenData.allArtifactGroups).fetchArtifactGroups()
    }

    override fun fetchArtifactsByGroupId(groupId: String): Single<List<ArtifactDTO>> {
        val artifacts = FakeGoogleMavenData.allArtifacts.filter {
            it.groupId == groupId.replace("/", ".")
        }
        return delegate.returningResponse(artifacts).fetchArtifactsByGroupId(groupId)
    }
}
