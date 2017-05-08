package app.http.response

import app.http.Headers
import app.http.StatusCode
import app.http.listHeaders
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by Vincente A. Campisi on 10/04/17.
 */
class BadRequestHttpResponse : HttpResponse {
    override val responseText = StringBuilder()
    override val headers = Headers()
    override val httpVersion = "HTTP/1.1"
    override val responseBody: ByteArray? = null

    init {
        this.responseText
                .append("$httpVersion ${StatusCode.BAD_REQUEST.code} ${StatusCode.BAD_REQUEST.description}\r\n")
        headers.addHeader("Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}")
        headers.addHeader("Connection:close")
        this.responseText.append(headers.getHeaderList().listHeaders())
    }

    override fun getResponseText(): String {
        return this.responseText.toString()
    }

}