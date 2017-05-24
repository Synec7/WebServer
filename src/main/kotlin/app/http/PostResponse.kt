package app.http

import http.Headers
import http.HttpStatusCode
import http.getHeader
import http.listHeaders
import http.request.HttpRequest
import http.response.HttpResponse
import java.security.MessageDigest
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
* Created by Vincente A. Campisi on 24/05/17.
*/
class PostResponse(val request: HttpRequest) : HttpResponse {

	override val responseText = StringBuilder()
	override val headers = Headers()
	override val httpVersion = "HTTP/1.1"
	override var responseBody: ByteArray? = null

	init {
		when (request.headers.getHeader("Content-Type")) {
			"application/octet-stream" -> createBinaryResponse()
			"application/json" -> createJsonResponse()
			else -> {
				throw IllegalArgumentException("Missing Content-Type header")
			}
		}

		this.responseText.append("$httpVersion ${HttpStatusCode.OK.code} ${HttpStatusCode.OK.description}\r\n")

		headers.addHeader("Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}")
		headers.addHeader("Connection:close")
		responseBody?.let { headers.addHeader("Content-Length:${responseBody!!.size}") }  //TODO: Improve this

		this.responseText.append(headers.getHeaderList().listHeaders())
	}

	override fun getResponseText(): String = this.responseText.toString()

	private fun createBinaryResponse() {
		responseBody = calculateHash(request.body).toByteArray(Charsets.UTF_8)
		headers.addHeader("Content-Type:text/plain;charset=utf-8")
	}

	private fun createJsonResponse() {
		responseBody = parseJsonToUtf8(request.body)
		headers.addHeader("Content-Type:application/json;charset=utf-8")
	}

	private fun calculateHash(body: ByteArray?) =
			if (body != null && body.isNotEmpty()) byteArrayToHex(MessageDigest.getInstance("MD5").digest(body))
			else throw IllegalArgumentException("Null request body.")

	private fun byteArrayToHex(a: ByteArray): String {
		val sb = StringBuilder(a.size * 2)
		for (b in a)
			sb.append(String.format("%02x", b))
		return sb.toString()
	}

	private fun parseJsonToUtf8(body: ByteArray?) =
			if (body != null && body.isNotEmpty()) String(body).toByteArray(Charsets.UTF_8)
			else throw IllegalArgumentException("Null request body.")
}

