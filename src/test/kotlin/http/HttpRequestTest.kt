package http

import org.junit.Assert.*

/**
 * Created by Vincente A. Campisi on 12/04/17.
 */
class HttpRequestTest {

    @org.junit.Test
    fun testConstructor() {
        val r = HttpRequest(HttpMethod.POST, listOf())
        assertNotNull(r)
        assertNull(r.body)
    }

    @org.junit.Test
    fun testBodyAndHeadersNotNull() {
        val r = HttpRequest(HttpMethod.POST, listOf(), ByteArray(45))
        assertNotNull(r.body)
        assertNotNull(r.headers)
    }
}