package reactivecircus.releaseprobe.remote.mock.artifact.api

import reactivecircus.releaseprobe.remote.FakeGoogleMavenData
import reactivecircus.releaseprobe.remote.artifact.api.GoogleMavenService
import reactivecircus.releaseprobe.remote.artifact.dto.ArtifactDTO
import reactivecircus.releaseprobe.remote.artifact.dto.ArtifactGroupDTO
import reactivecircus.releaseprobe.remote.artifact.toPath
import retrofit2.mock.BehaviorDelegate

class MockGoogleMavenService(private val delegate: BehaviorDelegate<GoogleMavenService>) :
    GoogleMavenService {

    override suspend fun fetchArtifactGroups(): List<ArtifactGroupDTO> {
        return delegate.returningResponse(FakeGoogleMavenData.allArtifactGroups)
            .fetchArtifactGroups()
    }

    override suspend fun fetchArtifactsByGroupId(groupId: String): List<ArtifactDTO> {
        val artifacts = FakeGoogleMavenData.allArtifacts.filter {
            it.groupId == groupId.toPath()
        }
        return delegate.returningResponse(artifacts).fetchArtifactsByGroupId(groupId)
    }
}
