package http.response

import http.listHeaders

/**
* Created by Vincente A. Campisi on 10/04/17.
*/
class NotImplementedHttpResponse : http.response.HttpResponse {
    override val responseText = StringBuilder()
    override val headers = http.Headers()
    override val httpVersion = "HTTP/1.1"
    override var responseBody: ByteArray? = null

    init {
        this.responseText
                .append("$httpVersion ${http.HttpStatusCode.NOT_IMPLEMENTED.code} ${http.HttpStatusCode.NOT_IMPLEMENTED.description}\r\n")
        headers.addHeader("Date:${java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME.format(java.time.ZonedDateTime.now(java.time.ZoneId.of("GMT")))}")
        headers.addHeader("Connection:close")
        this.responseText.append(headers.getHeaderList().listHeaders())
    }

    override fun getResponseText(): String = this.responseText.toString()
}