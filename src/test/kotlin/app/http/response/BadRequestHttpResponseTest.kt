package app.http.response

import app.http.BadRequestHttpResponse
import http.HttpStatusCode
import org.junit.Assert.*
import org.junit.Test
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
* Created by Vincente A. Campisi on 12/04/17.
*/
class BadRequestHttpResponseTest {
    @Test
    fun blankClass() {
        var response = "HTTP/1.1 ${HttpStatusCode.BAD_REQUEST.code} ${HttpStatusCode.BAD_REQUEST.description}\r\n"
        response += "Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}\r\n"
        response += "Connection:close\r\n"

        assertEquals(response, BadRequestHttpResponse().getResponseText())
    }


}