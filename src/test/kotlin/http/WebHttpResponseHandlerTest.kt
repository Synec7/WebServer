package http

import org.junit.Assert.*

/**
* Created by Vincente A. Campisi on 10/04/17.
*/
class WebHttpResponseHandlerTest {

    lateinit var rh: http.HttpResponseBuilder

    @org.junit.Before
    fun setup() {
        rh = http.HttpResponseBuilder()
    }

    @org.junit.Test
    fun buildUnknownResponse() {
        val response = rh.buildResponse(http.HttpRequest(HttpMethod.UNKNOWN, listOf(http.HttpHeader("", ""))))
        assertNotNull(response)
        var responseText = "HTTP/1.1 ${http.HttpStatusCode.BAD_REQUEST.code} ${http.HttpStatusCode.BAD_REQUEST.description}\r\n"
        responseText += "Date:${java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME.format(java.time.ZonedDateTime.now(java.time.ZoneId.of("GMT")))}\r\n"
        responseText += "Connection:close\r\n"
        assertEquals(responseText, response.getResponseText())
    }

    @org.junit.Test
    fun buildUnimplementedResponse() {
        val response = rh.buildResponse(http.HttpRequest(HttpMethod.GET, listOf(http.HttpHeader("", ""))))
        assertNotNull(response)
        var responseText = "HTTP/1.1 ${http.HttpStatusCode.NOT_IMPLEMENTED.code} ${http.HttpStatusCode.NOT_IMPLEMENTED.description}\r\n"
        responseText += "Date:${java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME.format(java.time.ZonedDateTime.now(java.time.ZoneId.of("GMT")))}\r\n"
        responseText += "Connection:close\r\n"
        assertEquals(responseText, response.getResponseText())
    }

    @org.junit.Test(expected = java.util.NoSuchElementException::class)
    fun buildMissingContentLengthHeaderPostResponse() {
        val response = rh.buildResponse(http.HttpRequest(
                HttpMethod.POST,
                listOf(http.HttpHeader("", "")),
                "abc".toByteArray(Charsets.ISO_8859_1))
        )
        assertNotNull(response)
        var responseText = "HTTP/1.1 ${http.HttpStatusCode.BAD_REQUEST.code} ${http.HttpStatusCode.BAD_REQUEST.description}\r\n"
        responseText += "Date:${java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME.format(java.time.ZonedDateTime.now(java.time.ZoneId.of("GMT")))}\r\n"
        responseText += "Connection:close\r\n"
        assertEquals(responseText, response.getResponseText())
    }

    @org.junit.Test(expected = java.util.NoSuchElementException::class)
    fun buildMissingContentTypeHeaderPostResponse() {
        val response = rh.buildResponse(http.HttpRequest(
                HttpMethod.POST,
                listOf(http.HttpHeader("", "")),
                "abc".toByteArray(Charsets.ISO_8859_1))
        )
        assertNotNull(response)
        var responseText = "HTTP/1.1 ${http.HttpStatusCode.BAD_REQUEST.code} ${http.HttpStatusCode.BAD_REQUEST.description}\r\n"
        responseText += "Date:${java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME.format(java.time.ZonedDateTime.now(java.time.ZoneId.of("GMT")))}\r\n"
        responseText += "Connection:close\r\n"
        assertEquals(responseText, response.getResponseText())
    }

    @org.junit.Test
    fun buildValidJsonResponse() {
        val response = rh.buildResponse(http.HttpRequest(
                HttpMethod.POST,
                listOf(http.HttpHeader("content-length", "3"), HttpHeader("content-type", "application/json")),
                "abc".toByteArray(Charsets.ISO_8859_1))
        )

        assertNotNull(response)
        var responseString = "HTTP/1.1 ${http.HttpStatusCode.OK.code} ${http.HttpStatusCode.OK.description}\r\n"
        responseString += "Content-Type:application/json;charset=utf-8\r\n"
        responseString += "Date:${java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME.format(java.time.ZonedDateTime.now(java.time.ZoneId.of("GMT")))}\r\n"
        responseString += "Connection:close\r\n"
        responseString += "Content-Length:3\r\n"

        assertEquals(responseString, response.getResponseText())
        assertEquals("abc", (response as http.HttpResponse).responseBody!!.toString(Charsets.UTF_8))
    }

    @org.junit.Test
    fun buildValidBinaryResponse() {
        val response = rh.buildResponse(http.HttpRequest(
                HttpMethod.POST,
                listOf(http.HttpHeader("content-length", "3"), HttpHeader("content-type", "application/octet-stream")),
                "abc".toByteArray(Charsets.ISO_8859_1))
        )

        var responseString = "HTTP/1.1 ${http.HttpStatusCode.OK.code} ${http.HttpStatusCode.OK.description}\r\n"
        responseString += "Content-Type:text/plain;charset=utf-8\r\n"
        responseString += "Date:${java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME.format(java.time.ZonedDateTime.now(java.time.ZoneId.of("GMT")))}\r\n"
        responseString += "Connection:close\r\n"
        responseString += "Content-Length:32\r\n"

        assertEquals(responseString, response.getResponseText())
        assertEquals("900150983cd24fb0d6963f7d28e17f72", (response as http.HttpResponse).responseBody!!.toString(Charsets.UTF_8))
    }

}