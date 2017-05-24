package http

import org.junit.Assert.*

/**
 * Created by Vincente A. Campisi on 27/03/17.
 */
class HttpHeaderTest {

    val myHeader = http.HttpHeader("host", "localhost:60074")

    @org.junit.Test
    fun notNullHeader() {
        assertNotNull(myHeader)
    }

    @org.junit.Test
    fun notNullFieldName() {
        assertNotNull(myHeader.fieldName)
    }

    @org.junit.Test
    fun notNullFieldValue() {
        assertNotNull(myHeader.fieldValue)
    }

    @org.junit.Test
    fun getFieldName() {
        assertEquals("host", myHeader.fieldName)
    }

    @org.junit.Test
    fun getNotFieldName() {
        assertEquals(false, myHeader.fieldName.equals("Incorrect"))
    }

    @org.junit.Test
    fun getFieldValue() {
        assertEquals("localhost:60074", myHeader.fieldValue)
    }

    @org.junit.Test
    fun getNotFieldValue() {
        assertEquals(false, myHeader.fieldValue.equals("Incorrect"))
    }

    @org.junit.Test
    fun toStringTest() {
        assertEquals("host:localhost:60074", myHeader.toString())
    }

    @org.junit.Test
    fun blankHeaderNotNull() {
        val myHeader = http.HttpHeader("", "")
        assertNotNull(myHeader)
    }

    @org.junit.Test
    fun blankHeader() {
        val myHeader = http.HttpHeader("", "")
        assertEquals(":", myHeader.toString())
    }

    @org.junit.Test
    fun notSameHeaders() {
        val myHeader2 = http.HttpHeader("host", "localhost:60074")
        assertNotSame(myHeader, myHeader2)
    }
}