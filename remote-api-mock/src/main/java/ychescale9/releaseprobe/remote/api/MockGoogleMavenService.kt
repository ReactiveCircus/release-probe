package ychescale9.releaseprobe.remote.api

import io.reactivex.Single
import retrofit2.mock.BehaviorDelegate
import ychescale9.releaseprobe.remote.dto.ArtifactGroupDTO

class MockGoogleMavenService(private val delegate: BehaviorDelegate<GoogleMavenService>) : GoogleMavenService {

    override fun fetchArtifactGroups(): Single<List<ArtifactGroupDTO>> {
        return delegate.returningResponse(FakeGoogleMavenData.allArtifactGroups).fetchArtifactGroups()
    }
}
