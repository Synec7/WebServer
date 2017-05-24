package app.http.request

import http.HttpMethod
import http.HttpMethodValidator
import org.junit.Assert.*
import org.junit.Test

/**
* Created by Vincente A. Campisi on 12/04/17.
*/
class HttpMethodValidatorTest {
    val m = HttpMethodValidator()

    @Test(expected = IllegalArgumentException::class)
    fun validateEmpty() {
        assertEquals(HttpMethod.UNKNOWN, m.validateMethod(""))
    }

    @Test(expected = IllegalArgumentException::class)
    fun validateInvalid() {
        assertEquals(HttpMethod.UNKNOWN, m.validateMethod("invalidString"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun validateUnknown() {
        assertEquals(HttpMethod.UNKNOWN, m.validateMethod("IDK localhost:60074 HTTP/1.1"))
    }

    @Test
    fun validateGet() {
        assertEquals(HttpMethod.GET, m.validateMethod("GET localhost:60074 HTTP/1.1"))
    }


}