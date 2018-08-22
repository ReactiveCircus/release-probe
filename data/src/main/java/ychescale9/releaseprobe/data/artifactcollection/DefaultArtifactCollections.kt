@file:Suppress("MaxLineLength")

package ychescale9.releaseprobe.data.artifactcollection

import javax.inject.Inject
import ychescale9.releaseprobe.persistence.artifactcollection.entity.ArtifactCollectionEntity

class DefaultArtifactCollections @Inject
constructor() {

    fun get(): List<ArtifactCollectionEntity> {
        return listOf(
                ArtifactCollectionEntity(
                        "AndroidX",
                        "Android extension libraries - a repackage of the Android Support Library, following semantic versioning",
                        "#4A8E33",
                        listOf("androidx", "com.google.android.material")
                ),
                ArtifactCollectionEntity(
                        "Firebase",
                        "Google's mobile platform for developing apps, improving app quality and growing business",
                        "#CF4900",
                        listOf("com.google.firebase", "com.crashlytics.sdk.android")
                ),
                ArtifactCollectionEntity(
                        "Android Tools",
                        "Various tools for building Android apps such as APK packaging, signing, and Gradle / IDE integrations",
                        "#414CBD",
                        listOf("com.android.tools")
                ),
                ArtifactCollectionEntity(
                        "Android Support Library",
                        "A collection of libraries focusing on backward compatibility for newer APIs",
                        "#C2185B",
                        listOf("com.android.support")
                ),
                ArtifactCollectionEntity(
                        "Android Things",
                        "SDKs for building smart and connected devices",
                        "#6C7075",
                        listOf("com.google.android.things")
                ),
                ArtifactCollectionEntity(
                        "AR",
                        "Libraries for working with ARCore",
                        "#A811B7",
                        listOf("com.google.ar")
                )
        )
    }
}
