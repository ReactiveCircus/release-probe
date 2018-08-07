package ychescale9.releaseprobe.remote.extension

import okhttp3.OkHttpClient

inline fun OkHttpClient.Builder.build(builder: OkHttpClient.Builder.() -> Unit): OkHttpClient {
    return OkHttpClient.Builder().apply {
        builder(this)
    }.build()
}
