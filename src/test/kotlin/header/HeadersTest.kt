package header

import http.Headers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 * Created by Vincente A. Campisi on 12/04/17.
 */
class HeadersTest {

    lateinit var hb: Headers

    @Before
    fun createHeaderBuilder() {
        hb = Headers()
    }

    @Test
    fun notNullHeaderReader() {
        assertNotNull(hb.getHeaderList())
        assertEquals(0, hb.getHeaderList().size)
    }

    @Test
    fun emptyBuilder() {
        assertEquals(0, hb.getHeaderList().size)
    }

    @Test
    fun addOneHeaderSuccess() {
        assertEquals(0, hb.getHeaderList().size)

        hb.addHeader("testName:testValue")
        assertEquals(1, hb.getHeaderList().size)
    }

    @Test(expected = IllegalArgumentException::class)
    fun addOneHeaderFail() {
        assertEquals(0, hb.getHeaderList().size)

        hb.addHeader("")
        assertEquals(1, hb.getHeaderList().size)
    }

    @Test
    fun addTwoOfSameHeader() {
        hb.addHeader("testName:testValue")
        hb.addHeader("testName:testValue")
        assert(hb.getHeaderList().size == 2)
        assertEquals(hb.getHeaderList()[0], hb.getHeaderList()[0])
        assertEquals(hb.getHeaderList()[0], hb.getHeaderList()[1])
    }
}