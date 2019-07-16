package ychescale9.releaseprobe.remote.real.artifact.interceptor

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.amshove.kluent.shouldEqual
import org.json.JSONObject
import org.junit.After
import org.junit.Before
import org.junit.Test

private const val MOCK_SERVER_PORT = 5000

class GoogleMavenResponseInterceptorTest {

    private val server = MockWebServer()

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(GoogleMavenResponseInterceptor())
    }.build()

    private val dummyRawXmlResponse = "<artifact.group>\n" +
            "<artifact1 versions=\"1.0.0,2.0.0,3.0.0\"/>\n" +
            "<artifact2 versions=\"3.0,3.1,3.2\"/>\n" +
            "</artifact.group>"

    @Before
    fun setUp() {
        // start mock server
        server.start(MOCK_SERVER_PORT)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `should convert raw XML response to JSON response when response is successful`() {
        server.enqueue(MockResponse().setResponseCode(200).setBody(dummyRawXmlResponse))
        val request = Request.Builder()
            .url(server.url("/"))
            .build()

        val newResponse = client.newCall(request).execute()

        newResponse.isSuccessful shouldEqual true

        JSONObject(newResponse.body!!.string()).run {
            has("artifact.group") shouldEqual true
            getJSONObject("artifact.group").run {
                has("artifact1") shouldEqual true
                getJSONObject("artifact1").getString("versions") shouldEqual "1.0.0,2.0.0,3.0.0"
                has("artifact2") shouldEqual true
                getJSONObject("artifact2").getString("versions") shouldEqual "3.0,3.1,3.2"
            }
        }
    }

    @Test
    fun `should NOT convert raw XML response to JSON response when response is unsuccessful`() {
        server.enqueue(MockResponse().setResponseCode(500))
        val request = Request.Builder()
            .url(server.url("/"))
            .build()

        val newResponse = client.newCall(request).execute()

        newResponse.isSuccessful shouldEqual false
        newResponse.body?.string() shouldEqual ""
    }
}
