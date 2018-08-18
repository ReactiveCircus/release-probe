package ychescale9.releaseprobe.remote

import ychescale9.releaseprobe.remote.artifact.dto.ArtifactDTO
import ychescale9.releaseprobe.remote.artifact.dto.ArtifactGroupDTO

object FakeGoogleMavenData {

    val allArtifactGroups: List<ArtifactGroupDTO>
        get() {
            return listOf(
                    // AndroidX
                    ArtifactGroupDTO("androidx.annotation"),
                    ArtifactGroupDTO("androidx.appcompat"),
                    ArtifactGroupDTO("androidx.arch.core"),
                    ArtifactGroupDTO("androidx.collection"),
                    ArtifactGroupDTO("androidx.constraintlayout"),
                    ArtifactGroupDTO("androidx.coordinatorlayout"),
                    ArtifactGroupDTO("androidx.core"),
                    ArtifactGroupDTO("androidx.fragment"),
                    ArtifactGroupDTO("androidx.paging"),
                    ArtifactGroupDTO("androidx.recyclerview"),
                    ArtifactGroupDTO("androidx.room"),
                    ArtifactGroupDTO("androidx.test"),
                    ArtifactGroupDTO("androidx.test.espresso"),
                    // Firebase
                    ArtifactGroupDTO("com.google.firebase"),
                    ArtifactGroupDTO("com.crashlytics.sdk.android"),
                    // Android Tools
                    ArtifactGroupDTO("com.android.tools"),
                    ArtifactGroupDTO("com.android.tools.build"),
                    ArtifactGroupDTO("com.android.tools.lint"),
                    // Android Support
                    ArtifactGroupDTO("com.android.support"),
                    ArtifactGroupDTO("com.android.support.constraint"),
                    // Android Things
                    ArtifactGroupDTO("com.google.android.things"),
                    // Android Wearable
                    ArtifactGroupDTO("com.google.android.wearable"),
                    // AR
                    ArtifactGroupDTO("com.google.ar"),
                    ArtifactGroupDTO("com.google.ar.sceneform")
            )
        }

    val allArtifacts: List<ArtifactDTO>
        get() {
            return listOf(
                    // AndroidX
                    ArtifactDTO(
                            "androidx.annotation",
                            "annotation",
                            "1.0.0-alpha1, 1.0.0-alpha3, 1.0.0-beta01, 1.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "androidx.appcompat",
                            "appcompat",
                            "1.0.0-alpha1, 1.0.0-alpha3, 1.0.0-beta01, 1.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "androidx.arch.core",
                            "core-common",
                            "2.0.0-alpha1, 2.0.0-beta01, 2.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "androidx.arch.core",
                            "core-runtime",
                            "2.0.0-alpha1, 2.0.0-beta01, 2.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "androidx.collection",
                            "collection",
                            "1.0.0-alpha1, 1.0.0-alpha3, 1.0.0-beta01, 1.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "androidx.collection",
                            "collection-ktx",
                            "1.0.0-alpha1, 1.0.0-alpha3, 1.0.0-beta01, 1.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "androidx.constraintlayout",
                            "constraintlayout",
                            "1.1.0, 1.1.1, 1.1.2, 2.0.0-alpha1, 2.0.0-alpha2"
                    ),
                    ArtifactDTO(
                            "androidx.coordinatorlayout",
                            "coordinatorlayout",
                            "1.0.0-alpha1, 1.0.0-alpha3, 1.0.0-beta01, 1.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "androidx.core",
                            "core",
                            "1.0.0-alpha1, 1.0.0-alpha3, 1.0.0-beta01, 1.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "androidx.core",
                            "core-ktx",
                            "1.0.0-alpha1, 1.0.0-alpha3, 1.0.0-beta01, 1.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "androidx.fragment",
                            "fragment",
                            "1.0.0-alpha1, 1.0.0-alpha3, 1.0.0-beta01, 1.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "androidx.fragment",
                            "fragment-ktx",
                            "1.0.0-alpha1, 1.0.0-alpha3, 1.0.0-beta01, 1.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "androidx.paging",
                            "paging-common",
                            "2.0.0-alpha1, 2.0.0-beta01, 2.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "androidx.paging",
                            "paging-runtime",
                            "2.0.0-alpha1, 2.0.0-beta01, 2.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "androidx.paging",
                            "paging-rxjava2",
                            "2.0.0-alpha1, 2.0.0-beta01, 2.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "androidx.recyclerview",
                            "recyclerview",
                            "1.0.0-alpha1, 1.0.0-alpha3, 1.0.0-beta01, 1.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "androidx.recyclerview",
                            "recyclerview-selection",
                            "1.0.0-alpha1, 1.0.0-alpha3, 1.0.0-beta01, 1.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "androidx.room",
                            "room-common",
                            "2.0.0-alpha1, 2.0.0-beta01, 2.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "androidx.room",
                            "room-compiler",
                            "2.0.0-alpha1, 2.0.0-beta01, 2.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "androidx.room",
                            "room-migration",
                            "2.0.0-alpha1, 2.0.0-beta01, 2.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "androidx.room",
                            "room-runtime",
                            "2.0.0-alpha1, 2.0.0-beta01, 2.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "androidx.room",
                            "room-rxjava2",
                            "2.0.0-alpha1, 2.0.0-beta01, 2.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "androidx.room",
                            "room-testing",
                            "2.0.0-alpha1, 2.0.0-beta01, 2.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "androidx.test",
                            "core",
                            "1.0.0-alpha2, 1.0.0-alpha3, 1.0.0-alpha4"
                    ),
                    ArtifactDTO(
                            "androidx.test",
                            "orchestrator",
                            "1.1.0-alpha1, 1.1.0-alpha2, 1.1.0-alpha3, 1.1.0-alpha4"
                    ),
                    ArtifactDTO(
                            "androidx.test",
                            "rules",
                            "1.1.0-alpha1, 1.1.0-alpha2, 1.1.0-alpha3, 1.1.0-alpha4"
                    ),
                    ArtifactDTO(
                            "androidx.test",
                            "runner",
                            "1.1.0-alpha1, 1.1.0-alpha2, 1.1.0-alpha3, 1.1.0-alpha4"
                    ),
                    ArtifactDTO(
                            "androidx.test.espresso",
                            "espresso-core",
                            "3.1.0-alpha1, 3.1.0-alpha2, 3.1.0-alpha3, 3.1.0-alpha4"
                    ),
                    ArtifactDTO(
                            "androidx.test.espresso",
                            "espresso-intents",
                            "3.1.0-alpha1, 3.1.0-alpha2, 3.1.0-alpha3, 3.1.0-alpha4"
                    ),
                    ArtifactDTO(
                            "androidx.test.espresso",
                            "espresso-web",
                            "3.1.0-alpha1, 3.1.0-alpha2, 3.1.0-alpha3, 3.1.0-alpha4"
                    ),
                    // Firebase
                    ArtifactDTO(
                            "com.google.firebase",
                            "firebase-analytics",
                            "12.0.0, 15.0.0, 16.0.0, 16.0.1"
                    ),
                    ArtifactDTO(
                            "com.google.firebase",
                            "firebase-auth",
                            "12.0.0, 15.0.0, 16.0.0, 16.0.1, 16.0.2"
                    ),
                    ArtifactDTO(
                            "com.google.firebase",
                            "firebase-firestore",
                            "12.0.0, 15.0.0, 16.0.0, 17.0.0, 17.0.1, 17.0.2"
                    ),
                    ArtifactDTO(
                            "com.google.firebase",
                            "firebase-messaging",
                            "12.0.0, 15.0.0, 16.0.0, 17.0.0, 17.1.0, 17.2.0"
                    ),
                    ArtifactDTO(
                            "com.crashlytics.sdk.android",
                            "answer",
                            "1.3.13, 1.4.0, 1.4.1, 1.4.2"
                    ),
                    ArtifactDTO(
                            "com.crashlytics.sdk.android",
                            "beta",
                            "1.2.5, 1.2.6, 1.2.7, 1.2.8"
                    ),
                    ArtifactDTO(
                            "com.crashlytics.sdk.android",
                            "crashlytics",
                            "2.6.8, 2.7.0, 2.7.1, 2.8.0, 2.9.0, 2.9.4"
                    ),
                    // Android Tools
                    ArtifactDTO(
                            "com.android.tools",
                            "common",
                            "26.2.0-alpha01, 26.2.0-alpha02, 26.2.0-alpha03"
                    ),
                    ArtifactDTO(
                            "com.android.tools",
                            "r8",
                            "1.0.10, 1.0.18, 1.0.18, 1.0.36"
                    ),
                    ArtifactDTO(
                            "com.android.tools.build",
                            "aapt2",
                            "3.3.0-alpha01, 3.3.0-alpha02, 3.3.0-alpha03, 3.3.0-alpha04, 3.3.0-alpha05"
                    ),
                    ArtifactDTO(
                            "com.android.tools.build",
                            "apksig",
                            "3.3.0-alpha01, 3.3.0-alpha02, 3.3.0-alpha03, 3.3.0-alpha04, 3.3.0-alpha05"
                    ),
                    ArtifactDTO(
                            "com.android.tools.build",
                            "bundletool",
                            "0.2.0, 0.3.0, 0.4.0, 0.5.0"
                    ),
                    ArtifactDTO(
                            "com.android.tools.build",
                            "gradle",
                            "3.3.0-alpha01, 3.3.0-alpha02, 3.3.0-alpha03, 3.3.0-alpha04, 3.3.0-alpha05"
                    ),
                    ArtifactDTO(
                            "com.android.tools.lint",
                            "lint",
                            "26.2.0-alpha01, 26.2.0-alpha02, 26.2.0-alpha03"
                    ),
                    ArtifactDTO(
                            "com.android.tools.lint",
                            "lint-kotlin",
                            "26.2.0-alpha01, 26.2.0-alpha02, 26.2.0-alpha03"
                    ),
                    // Android Support
                    ArtifactDTO(
                            "com.android.support",
                            "animated-vector-drawable",
                            "27.0.0, 28.0.0-alpha1, 28.0.0-alpha1, 28.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "com.android.support",
                            "appcompat-v7",
                            "27.0.0, 28.0.0-alpha1, 28.0.0-alpha1, 28.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "com.android.support",
                            "cardview-v7",
                            "27.0.0, 28.0.0-alpha1, 28.0.0-alpha1, 28.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "com.android.support",
                            "collections",
                            "28.0.0-alpha1, 28.0.0-alpha1, 28.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "com.android.support",
                            "design",
                            "27.0.0, 28.0.0-alpha1, 28.0.0-alpha1, 28.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "com.android.support",
                            "recyclerview-v7",
                            "27.0.0, 28.0.0-alpha1, 28.0.0-alpha1, 28.0.0-rc01"
                    ),
                    ArtifactDTO(
                            "com.android.support.constraint",
                            "constraint-layout",
                            "1.0.2, 1.1.0, 1.1.1, 2.0.0-alpha1, 2.0.0-alpha2"
                    ),
                    // Android Things
                    ArtifactDTO(
                            "com.google.android.things",
                            "androidthings",
                            "0.6.1-devpreview, 0.7-devpreview, 0.8-devpreview, 1.0"
                    ),
                    // Android Wearable
                    ArtifactDTO(
                            "com.google.android.wearable",
                            "wearable",
                            "2.1.0, 2.2.0, 2.3.0"
                    ),
                    // AR
                    ArtifactDTO(
                            "com.google.ar",
                            "core",
                            "1.0.0, 1.1.0, 1.2.0"
                    ),
                    ArtifactDTO(
                            "com.google.ar.sceneform",
                            "core",
                            "1.0.0, 1.3.0, 1.4.0"
                    ),
                    ArtifactDTO(
                            "com.google.ar.sceneform",
                            "filament-android",
                            "1.0.0, 1.3.0, 1.4.0"
                    )
            )
        }
}
