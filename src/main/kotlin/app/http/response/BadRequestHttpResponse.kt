package app.http.response

import app.http.listHeaders

/**
 * Created by Vincente A. Campisi on 10/04/17.
 */
class BadRequestHttpResponse : HttpResponse {
    override val responseText = StringBuilder()
    override val headers = app.http.Headers()
    override val httpVersion = "HTTP/1.1"
    override val responseBody: ByteArray? = null

    init {
        this.responseText
                .append("$httpVersion ${app.http.StatusCode.BAD_REQUEST.code} ${app.http.StatusCode.BAD_REQUEST.description}\r\n")
        headers.addHeader("Date:${java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME.format(java.time.ZonedDateTime.now(java.time.ZoneId.of("GMT")))}")
        headers.addHeader("Connection:close")
        this.responseText.append(headers.getHeaderList().listHeaders())
    }

    override fun getResponseText(): String {
        return this.responseText.toString()
    }

}