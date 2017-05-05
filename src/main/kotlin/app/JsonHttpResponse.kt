package app

import http.Headers
import http.StatusCode
import http.listHeaders

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by Vincente A. Campisi on 10/04/17.
 */
class JsonHttpResponse(requestBody: ByteArray?) : HttpResponse {
    override val responseText = StringBuilder()
    override val headers = Headers()
    override val httpVersion = "HTTP/1.1"
    override val responseBody: ByteArray = parseJsonToUtf8(requestBody)

    init {
        this.responseText.append("$httpVersion ${StatusCode.OK.code} ${StatusCode.OK.description}\r\n")
        headers.addHeader("Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}")
        headers.addHeader("Connection:close")
        headers.addHeader("Content-Length:${this.responseBody.size}")
        headers.addHeader("Content-Type:application/json;charset=utf-8")
        this.responseText.append(headers.getHeaderList().listHeaders())
    }

    override fun getResponseText(): String = this.responseText.toString()

    private fun parseJsonToUtf8(body: ByteArray?) =
            if (body != null && body.size > 0) String(body).toByteArray(Charsets.UTF_8)
            else throw IllegalArgumentException("Null request body.")
}