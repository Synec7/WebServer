package http

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
* Created by Vincente A. Campisi on 10/04/17.
*/
class NotImplementedHttpResponse : HttpResponse {
    override val responseText = StringBuilder()
    override val headers = Headers()
    override val httpVersion = "HTTP/1.1"
    override var responseBody: ByteArray? = null

    init {
        this.responseText
                .append("$httpVersion ${HttpStatusCode.NOT_IMPLEMENTED.code} ${HttpStatusCode.NOT_IMPLEMENTED.description}\r\n")
        headers.addHeader("Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}")
        headers.addHeader("Connection:close")
        this.responseText.append(headers.getHeaderList().listHeaders())
    }

    override fun getResponseText(): String = this.responseText.toString()
}