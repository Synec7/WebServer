package app.http.request

import app.http.HttpMethod
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * Created by Vincente A. Campisi on 12/04/17.
 */
class HttpRequestTest {

    @Test
    fun testConstructor() {
        val r = HttpRequest(HttpMethod.POST, listOf())
        assertNotNull(r)
        assertNull(r.body)
    }

    @Test
    fun testBodyAndHeadersNotNull() {
        val r = HttpRequest(HttpMethod.POST, listOf(), ByteArray(45))
        assertNotNull(r.body)
        assertNotNull(r.headers)
    }
}