package app.http

import http.response.NotImplementedHttpResponse

/**
* Created by Vincente A. Campisi on 19/04/17.
*/
class NotImplementedHttpResponseTest {

    @org.junit.Test
    fun blankClass() {
        var response = "HTTP/1.1 ${http.HttpStatusCode.NOT_IMPLEMENTED.code} ${http.HttpStatusCode.NOT_IMPLEMENTED.description}\r\n"
        response += "Date:${java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME.format(java.time.ZonedDateTime.now(java.time.ZoneId.of("GMT")))}\r\n"
        response += "Connection:close\r\n"

        org.junit.Assert.assertEquals(response, NotImplementedHttpResponse().getResponseText())
    }
}