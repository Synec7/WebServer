package app.http.response

import http.HttpHeader
import http.HttpMethod
import http.HttpRequest
import http.HttpResponse
import http.HttpResponseBuilder
import http.HttpStatusCode
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
* Created by Vincente A. Campisi on 10/04/17.
*/
class WebHttpResponseHandlerTest {

    lateinit var rh: HttpResponseBuilder

    @Before
    fun setup() {
        rh = HttpResponseBuilder()
    }

    @Test
    fun buildUnknownResponse() {
        val response = rh.buildResponse(HttpRequest(HttpMethod.UNKNOWN, listOf(HttpHeader("", ""))))
        assertNotNull(response)
        var responseText = "HTTP/1.1 ${HttpStatusCode.BAD_REQUEST.code} ${HttpStatusCode.BAD_REQUEST.description}\r\n"
        responseText += "Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}\r\n"
        responseText += "Connection:close\r\n"
        assertEquals(responseText, response.getResponseText())
    }

    @Test
    fun buildUnimplementedResponse() {
        val response = rh.buildResponse(HttpRequest(HttpMethod.GET, listOf(HttpHeader("", ""))))
        assertNotNull(response)
        var responseText = "HTTP/1.1 ${HttpStatusCode.NOT_IMPLEMENTED.code} ${HttpStatusCode.NOT_IMPLEMENTED.description}\r\n"
        responseText += "Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}\r\n"
        responseText += "Connection:close\r\n"
        assertEquals(responseText, response.getResponseText())
    }

    @Test(expected = NoSuchElementException::class)
    fun buildMissingContentLengthHeaderPostResponse() {
        val response = rh.buildResponse(HttpRequest(
                HttpMethod.POST,
                listOf(HttpHeader("", "")),
                "abc".toByteArray(Charsets.ISO_8859_1))
        )
        assertNotNull(response)
        var responseText = "HTTP/1.1 ${HttpStatusCode.BAD_REQUEST.code} ${HttpStatusCode.BAD_REQUEST.description}\r\n"
        responseText += "Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}\r\n"
        responseText += "Connection:close\r\n"
        assertEquals(responseText, response.getResponseText())
    }

    @Test(expected = NoSuchElementException::class)
    fun buildMissingContentTypeHeaderPostResponse() {
        val response = rh.buildResponse(HttpRequest(
                HttpMethod.POST,
                listOf(HttpHeader("", "")),
                "abc".toByteArray(Charsets.ISO_8859_1))
        )
        assertNotNull(response)
        var responseText = "HTTP/1.1 ${HttpStatusCode.BAD_REQUEST.code} ${HttpStatusCode.BAD_REQUEST.description}\r\n"
        responseText += "Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}\r\n"
        responseText += "Connection:close\r\n"
        assertEquals(responseText, response.getResponseText())
    }

    @Test
    fun buildValidJsonResponse() {
        val response = rh.buildResponse(HttpRequest(
                HttpMethod.POST,
                listOf(HttpHeader("content-length", "3"), HttpHeader("content-type", "application/json")),
                "abc".toByteArray(Charsets.ISO_8859_1))
        )

        assertNotNull(response)
        var responseString = "HTTP/1.1 ${HttpStatusCode.OK.code} ${HttpStatusCode.OK.description}\r\n"
        responseString += "Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}\r\n"
        responseString += "Connection:close\r\n"
        responseString += "Content-Length:3\r\n"
        responseString += "Content-Type:application/json;charset=utf-8\r\n"

        assertEquals(responseString, response.getResponseText())
        assertEquals("abc", (response as HttpResponse).responseBody!!.toString(Charsets.UTF_8))
    }

    @Test
    fun buildValidBinaryResponse() {
        val response = rh.buildResponse(HttpRequest(
                HttpMethod.POST,
                listOf(HttpHeader("content-length", "3"), HttpHeader("content-type", "application/octet-stream")),
                "abc".toByteArray(Charsets.ISO_8859_1))
        )

        var responseString = "HTTP/1.1 ${HttpStatusCode.OK.code} ${HttpStatusCode.OK.description}\r\n"
        responseString += "Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}\r\n"
        responseString += "Connection:close\r\n"
        responseString += "Content-Length:32\r\n"
        responseString += "Content-Type:text/plain;charset=utf-8\r\n"

        assertEquals(responseString, response.getResponseText())
        assertEquals("900150983cd24fb0d6963f7d28e17f72", (response as HttpResponse).responseBody!!.toString(Charsets.UTF_8))
    }

    @Test(expected = IllegalArgumentException::class)
    fun buildEmptyBodyJsonResponse() {
        val response = rh.buildResponse(HttpRequest(
                HttpMethod.POST,
                listOf(HttpHeader("content-length", "3"), HttpHeader("content-type", "application/octet-stream")),
                "".toByteArray(Charsets.ISO_8859_1))
        )
        assertNotNull(response)
    }

}