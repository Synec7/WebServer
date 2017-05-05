package response

import app.BinaryHttpResponse
import http.StatusCode
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by Vincente A. Campisi on 19/04/17.
 */
class BinaryHttpResponseTest {

    @Test(expected = IllegalArgumentException::class)
    fun nullBody() {
        var br = BinaryHttpResponse(ByteArray(0))
    }

    @Test
    fun abc() {
        var br = BinaryHttpResponse("abc".toByteArray(Charsets.ISO_8859_1))
        assertNotNull(br)
        assertNotNull(br.headers)
        assertNotNull(br.responseBody)
        assertNotNull(br.responseText)
        assertEquals(br.httpVersion, "HTTP/1.1")

        var response = "HTTP/1.1 ${StatusCode.OK.code} ${StatusCode.OK.description}\r\n"
        response += "Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}\r\n"
        response += "Connection:close\r\n"
        response += "Content-Length:32\r\n"
        response += "Content-Type:text/plain;charset=utf-8\r\n"

        assertEquals(response, br.getResponseText())
        assertEquals("900150983cd24fb0d6963f7d28e17f72", br.responseBody.toString(Charsets.UTF_8))
    }

}