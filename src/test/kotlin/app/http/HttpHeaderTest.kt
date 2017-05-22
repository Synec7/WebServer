package app.http

import http.HttpHeader
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Vincente A. Campisi on 27/03/17.
 */
class HttpHeaderTest {

    val myHeader = HttpHeader("host", "localhost:60074")

    @Test
    fun notNullHeader() {
        assertNotNull(myHeader)
    }

    @Test
    fun notNullFieldName() {
        assertNotNull(myHeader.fieldName)
    }

    @Test
    fun notNullFieldValue() {
        assertNotNull(myHeader.fieldValue)
    }

    @Test
    fun getFieldName() {
        assertEquals("host", myHeader.fieldName)
    }

    @Test
    fun getNotFieldName() {
        assertEquals(false, myHeader.fieldName.equals("Incorrect"))
    }

    @Test
    fun getFieldValue() {
        assertEquals("localhost:60074", myHeader.fieldValue)
    }

    @Test
    fun getNotFieldValue() {
        assertEquals(false, myHeader.fieldValue.equals("Incorrect"))
    }

    @Test
    fun toStringTest() {
        assertEquals("host:localhost:60074", myHeader.toString())
    }

    @Test
    fun blankHeaderNotNull() {
        val myHeader = HttpHeader("", "")
        assertNotNull(myHeader)
    }

    @Test
    fun blankHeader() {
        val myHeader = HttpHeader("", "")
        assertEquals(":", myHeader.toString())
    }

    @Test
    fun notSameHeaders() {
        val myHeader2 = HttpHeader("host", "localhost:60074")
        assertNotSame(myHeader, myHeader2)
    }
}