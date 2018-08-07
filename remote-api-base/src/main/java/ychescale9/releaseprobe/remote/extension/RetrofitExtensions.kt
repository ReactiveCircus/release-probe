package ychescale9.releaseprobe.remote.extension

import retrofit2.Retrofit

inline fun Retrofit.Builder.build(builder: Retrofit.Builder.() -> Unit): Retrofit {
    return Retrofit.Builder().apply {
        builder(this)
    }.build()
}
