package ychescale9.releaseprobe.remote.real.artifact.interceptor

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject
import org.json.XML
import java.io.IOException

/**
 * Converts XML response to JSON for Google Maven API responses
 */
class GoogleMavenResponseInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (response.isSuccessful) {
            val jsonObject: JSONObject = XML.toJSONObject(response.body?.string())
            val contentType = "application/json; charset=utf-8".toMediaTypeOrNull()
            val newBody = jsonObject.toString().toResponseBody(contentType)
            return response.newBuilder().body(newBody).build()
        }
        return response
    }
}
