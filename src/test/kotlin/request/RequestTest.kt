package request

import method.HttpMethod
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * Created by Vincente A. Campisi on 12/04/17.
 */
class RequestTest {

    @Test
    fun testConstructor() {
        val r = Request(HttpMethod.POST, listOf())
        assertNotNull(r)
        assertNull(r.body)
    }

    @Test
    fun testBodyAndHeadersNotNull() {
        val r = Request(HttpMethod.POST, listOf(), ByteArray(45))
        assertNotNull(r.body)
        assertNotNull(r.headers)
    }
}