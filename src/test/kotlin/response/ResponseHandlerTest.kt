package response

import header.HttpHeader
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import method.HttpMethod
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

//
//    @Test
//    fun buildInvalidBodyPostResponse() {
//        val rh = ResponseHandler(System.out)
//        val responseString = "HTTP/1.1 ${StatusCode.BAD_REQUEST.code} ${StatusCode.BAD_REQUEST.description}\r\n" +
//                "Date:Mon, 10 Apr 2017 07\r\nConnection:close\r\n\r\n"
//        assertEquals(responseString, rh.buildResponse(HttpMethod.POST, "application/json", null))
//    }
//
//    @Test
//    fun buildValidJsonPostResponse() {
//        val rh = ResponseHandler(System.out)
//        val responseString = "HTTP/1.1 ${StatusCode.OK.code} ${StatusCode.OK.description}\r\n" +
//                "Date:Mon, 10 Apr 2017 07\r\nConnection:close\r\n\r\n" +
//                "ABC".toByteArray(Charsets.UTF_8).toString(Charsets.UTF_8)
//        assertEquals(responseString, rh.buildResponse(HttpMethod.POST, "application/json", "ABC".toByteArray()) +
//                rh.response.getResponseBody()!!.toString(Charsets.UTF_8))
//    }
//
//    @Test
//    fun calculateMd5HashHexValidInput() {
//        val rh = ResponseHandler(System.out)
//        val path = Paths.get("/Users/vincente.campisi/IdeaProjects/webserver/src/test/kotlin/response/3765")
//        assertEquals("0c2da7ba464827e2fb4fbcb6ef36c6ba", rh.calculateHash(Files.readAllBytes(path)))
//    }

}