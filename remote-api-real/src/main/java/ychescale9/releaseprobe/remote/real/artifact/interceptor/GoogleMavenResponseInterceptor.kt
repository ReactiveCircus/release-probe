package ychescale9.releaseprobe.remote.real.artifact.interceptor

import java.io.IOException
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONObject
import org.json.XML

/**
 * Converts XML response to JSON for Google Maven API responses
 */
class GoogleMavenResponseInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (response.isSuccessful) {
            val jsonObject: JSONObject = XML.toJSONObject(response.body()?.string())
            val contentType = MediaType.parse("application/json; charset=utf-8")
            val newBody = ResponseBody.create(contentType, jsonObject.toString())
            return response.newBuilder().body(newBody).build()
        }
        return response
    }
}
