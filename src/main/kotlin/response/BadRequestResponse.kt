package response

import header.HeaderBuilder
import header.listHeaders
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by Vincente A. Campisi on 10/04/17.
 */
class BadRequestResponse : Response {
    override val responseText = StringBuilder()
    override val headers = HeaderBuilder()
    override val httpVersion = "HTTP/1.1"
    override val responseBody: ByteArray? = null

    init {
        this.responseText
                .append("$httpVersion ${StatusCode.BAD_REQUEST.code} ${StatusCode.BAD_REQUEST.description}\r\n")
        headers.addHeader("Date:${DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")))}")
        headers.addHeader("Connection:close")
        this.responseText.append(headers.getHeaderReader().listHeaders())
    }

    override fun getResponseText(): String {
        return this.responseText.toString()
    }

}