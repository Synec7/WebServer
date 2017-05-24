package app.http

import http.HttpHeader
import http.HttpMethod
import http.HttpStatusCode
import http.request.HttpRequest
import org.junit.Assert
import org.junit.Test
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by Vincente A. Campisi on 24/05/17.
 */
class PostResponseTest {

	@Test(expected = IllegalArgumentException::class)
	fun nullBodyJson() {
		val request = HttpRequest(HttpMethod.POST, listOf(HttpHeader("Content-Length", "3"), HttpHeader("Content-Type", "application/json")), null)
		val br = PostResponse(request)
		Assert.assertNotNull(br)
	}

	@Test
	fun abcJson() {
		val request = HttpRequest(HttpMethod.POST, listOf(HttpHeader("Content-Length", "3"), HttpHeader("Content-Type", "application/json")), "abc".toByteArray(Charsets.ISO_8859_1))
		val br = PostResponse(request)
		Assert.assertNotNull(br)
		Assert.assertNotNull(br.headers)
		Assert.assertNotNull(br.responseBody)
		Assert.assertNotNull(br.responseText)
		Assert.assertEquals(br.httpVersion, "HTTP/1.1")

		var response = "HTTP/1.1 ${HttpStatusCode.OK.code} ${HttpStatusCode.OK.description}\r\n"
		response += "Content-Type:application/json;charset=utf-8\r\n"
		response += "Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}\r\n"
		response += "Connection:close\r\n"
		response += "Content-Length:3\r\n"

		Assert.assertEquals(response, br.getResponseText())
		Assert.assertEquals("abc", br.responseBody!!.toString(Charsets.UTF_8))
	}

	@Test(expected = IllegalArgumentException::class)
	fun nullBodyBinary() {
		val br = PostResponse(HttpRequest(HttpMethod.POST, listOf(HttpHeader("Content-Type", "application/octet-stream")), null))
		Assert.assertNotNull(br)
	}

	@Test
	fun abcBinary() {
		val br = PostResponse(HttpRequest(HttpMethod.POST, listOf(HttpHeader("Content-Type", "application/octet-stream"), HttpHeader("Content-Length", "3")), "abc".toByteArray(Charsets.ISO_8859_1)))
		Assert.assertNotNull(br)
		Assert.assertNotNull(br.headers)
		Assert.assertNotNull(br.responseBody)
		Assert.assertNotNull(br.responseText)
		Assert.assertEquals(br.httpVersion, "HTTP/1.1")

		var response = "HTTP/1.1 ${HttpStatusCode.OK.code} ${HttpStatusCode.OK.description}\r\n"
		response += "Content-Type:text/plain;charset=utf-8\r\n"
		response += "Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}\r\n"
		response += "Connection:close\r\n"
		response += "Content-Length:3\r\n"

		Assert.assertEquals("900150983cd24fb0d6963f7d28e17f72", br.responseBody?.toString(Charsets.UTF_8))
	}
}