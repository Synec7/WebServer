package header

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 * Created by Vincente A. Campisi on 12/04/17.
 */
class HeaderBuilderTest {

    lateinit var hb: HeaderBuilder

    @Before
    fun createHeaderBuilder() {
        hb = HeaderBuilder()
    }

    @Test
    fun notNullHeaderReader() {
        assertNotNull(hb.getHeaderReader())
        assertEquals(0, hb.getHeaderReader().size)
    }

    @Test
    fun emptyBuilder() {
        assertEquals(0, hb.getHeaderReader().size)
    }

    @Test
    fun addOneHeaderSuccess() {
        assertEquals(0, hb.getHeaderReader().size)

        hb.addHeader("testName:testValue")
        assertEquals(1, hb.getHeaderReader().size)
    }

    @Test(expected = IllegalArgumentException::class)
    fun addOneHeaderFail() {
        assertEquals(0, hb.getHeaderReader().size)

        hb.addHeader("")
        println(hb.getHeaderReader().listHeaders())
        assertEquals(1, hb.getHeaderReader().size)
    }

    @Test
    fun addTwoOfSameHeader() {
        hb.addHeader("testName:testValue")
        hb.addHeader("testName:testValue")
        assert(hb.getHeaderReader().size == 2)
        assertEquals(hb.getHeaderReader()[0], hb.getHeaderReader()[0])
        assertEquals(hb.getHeaderReader()[0], hb.getHeaderReader()[1])
    }
}