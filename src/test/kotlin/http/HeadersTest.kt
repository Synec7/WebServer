package http

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Vincente A. Campisi on 12/04/17.
 */
class HeadersTest {

    lateinit var hb: http.Headers

    @org.junit.Before
    fun createHeaders() {
        hb = http.Headers()
    }

    @org.junit.Test
    fun notNullHeaderReader() {
        assertNotNull(hb.getHeaderList())
    }

    @org.junit.Test
    fun emptyBuilder() {
        assertEquals(0, hb.getHeaderList().size)
    }

    @org.junit.Test
    fun addOneHeaderSuccess() {
        assertEquals(0, hb.getHeaderList().size)

        hb.addHeader("testName:testValue")
        assertEquals(1, hb.getHeaderList().size)
    }

    @org.junit.Test(expected = IllegalArgumentException::class)
    fun addOneHeaderFail() {
        assertEquals(0, hb.getHeaderList().size)

        hb.addHeader("")
        assertEquals(1, hb.getHeaderList().size)
    }

    @org.junit.Test
    fun addTwoOfSameHeader() {
        hb.addHeader("testName:testValue")
        hb.addHeader("testName:testValue")
        assert(hb.getHeaderList().size == 2)
        assertEquals(hb.getHeaderList()[0], hb.getHeaderList()[0])
        assertEquals(hb.getHeaderList()[0], hb.getHeaderList()[1])
    }

    @Test(expected = IllegalArgumentException::class)
    fun addTwoOneFail() {
        hb.addHeader("testName:testValue")
        assert(hb.getHeaderList().size == 1)
        hb.addHeader("")
    }
}