package response

import header.HttpHeader
import method.HttpMethod
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import request.Request
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Created by Vincente A. Campisi on 10/04/17.
 */
class ResponseHandlerTest {

    lateinit var rh: ResponseHandler

    @Before
    fun setup() {
        rh = ResponseHandler(System.out)
    }

    @Test
    fun buildUnknownResponse() {
        val response = rh.buildResponse(Request(HttpMethod.UNKNOWN, listOf(HttpHeader("", ""))))
        assertNotNull(response)
        var responseText = "HTTP/1.1 ${StatusCode.BAD_REQUEST.code} ${StatusCode.BAD_REQUEST.description}\r\n"
        responseText += "Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}\r\n"
        responseText += "Connection:close\r\n"
        assertEquals(responseText, response.getResponseText())
    }

    @Test
    fun buildUnimplementedResponse() {
        val response = rh.buildResponse(Request(HttpMethod.GET, listOf(HttpHeader("", ""))))
        assertNotNull(response)
        var responseText = "HTTP/1.1 ${StatusCode.NOT_IMPLEMENTED.code} ${StatusCode.NOT_IMPLEMENTED.description}\r\n"
        responseText += "Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}\r\n"
        responseText += "Connection:close\r\n"
        assertEquals(responseText, response.getResponseText())
    }

    @Test(expected = NoSuchElementException::class)
    fun buildMissingContentLengthHeaderPostResponse() {
        val response = rh.buildResponse(Request(
                HttpMethod.POST,
                listOf(HttpHeader("", "")),
                "abc".toByteArray(Charsets.ISO_8859_1))
        )
        assertNotNull(response)
        var responseText = "HTTP/1.1 ${StatusCode.BAD_REQUEST.code} ${StatusCode.BAD_REQUEST.description}\r\n"
        responseText += "Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}\r\n"
        responseText += "Connection:close\r\n"
        assertEquals(responseText, response.getResponseText())
    }

    @Test(expected = NoSuchElementException::class)
    fun buildMissingContentTypeHeaderPostResponse() {
        val response = rh.buildResponse(Request(
                HttpMethod.POST,
                listOf(HttpHeader("", "")),
                "abc".toByteArray(Charsets.ISO_8859_1))
        )
        assertNotNull(response)
        var responseText = "HTTP/1.1 ${StatusCode.BAD_REQUEST.code} ${StatusCode.BAD_REQUEST.description}\r\n"
        responseText += "Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}\r\n"
        responseText += "Connection:close\r\n"
        assertEquals(responseText, response.getResponseText())
    }

    @Test
    fun buildValidJsonResponse() {
        val response = rh.buildResponse(Request(
                HttpMethod.POST,
                listOf(HttpHeader("content-length", "3"), HttpHeader("content-type", "application/json")),
                "abc".toByteArray(Charsets.ISO_8859_1))
        )

        assertNotNull(response)
        var responseString = "HTTP/1.1 ${StatusCode.OK.code} ${StatusCode.OK.description}\r\n"
        responseString += "Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}\r\n"
        responseString += "Connection:close\r\n"
        responseString += "Content-Length:3\r\n"
        responseString += "Content-Type:application/json;charset=utf-8\r\n"

        assertEquals(responseString, response.getResponseText())
        assertEquals("abc", response.responseBody!!.toString(Charsets.UTF_8))
    }

    @Test
    fun buildValidBinaryResponse() {
        val response = rh.buildResponse(Request(
                HttpMethod.POST,
                listOf(HttpHeader("content-length", "3"), HttpHeader("content-type", "application/octet-stream")),
                "abc".toByteArray(Charsets.ISO_8859_1))
        )

        var responseString = "HTTP/1.1 ${StatusCode.OK.code} ${StatusCode.OK.description}\r\n"
        responseString += "Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}\r\n"
        responseString += "Connection:close\r\n"
        responseString += "Content-Length:32\r\n"
        responseString += "Content-Type:text/plain;charset=utf-8\r\n"

        assertEquals(responseString, response.getResponseText())
        assertEquals("900150983cd24fb0d6963f7d28e17f72", response.responseBody!!.toString(Charsets.UTF_8))
    }

    @Test(expected = IllegalArgumentException::class)
    fun buildEmptyBodyJsonResponse() {
        val response = rh.buildResponse(Request(
                HttpMethod.POST,
                listOf(HttpHeader("content-length", "3"), HttpHeader("content-type", "application/octet-stream")),
                "".toByteArray(Charsets.ISO_8859_1))
        )
    }

}