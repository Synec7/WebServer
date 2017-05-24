package app.http.response

import app.http.NotImplementedHttpResponse
import http.HttpStatusCode
import org.junit.Assert
import org.junit.Test
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
* Created by Vincente A. Campisi on 19/04/17.
*/
class NotImplementedHttpResponseTest {

    @Test
    fun blankClass() {
        var response = "HTTP/1.1 ${HttpStatusCode.NOT_IMPLEMENTED.code} ${HttpStatusCode.NOT_IMPLEMENTED.description}\r\n"
        response += "Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}\r\n"
        response += "Connection:close\r\n"

        Assert.assertEquals(response, NotImplementedHttpResponse().getResponseText())
    }
}