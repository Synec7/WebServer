package http

import org.junit.Assert.*

/**
* Created by Vincente A. Campisi on 12/04/17.
*/
class HttpMethodValidatorTest {
    val m = http.HttpMethodValidator()

    @org.junit.Test(expected = IllegalArgumentException::class)
    fun validateEmpty() {
        assertEquals(http.HttpMethod.UNKNOWN, m.validateMethod(""))
    }

    @org.junit.Test(expected = IllegalArgumentException::class)
    fun validateInvalid() {
        assertEquals(http.HttpMethod.UNKNOWN, m.validateMethod("invalidString"))
    }

    @org.junit.Test(expected = IllegalArgumentException::class)
    fun validateUnknown() {
        assertEquals(http.HttpMethod.UNKNOWN, m.validateMethod("IDK localhost:60074 HTTP/1.1"))
    }

    @org.junit.Test
    fun validateGet() {
        assertEquals(http.HttpMethod.GET, m.validateMethod("GET localhost:60074 HTTP/1.1"))
    }


}