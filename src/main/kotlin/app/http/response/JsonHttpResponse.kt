package app.http.response

import app.http.listHeaders

/**
 * Created by Vincente A. Campisi on 10/04/17.
 */
class JsonHttpResponse(requestBody: ByteArray?) : HttpResponse {
    override val responseText = StringBuilder()
    override val headers = app.http.Headers()
    override val httpVersion = "HTTP/1.1"
    override val responseBody: ByteArray = parseJsonToUtf8(requestBody)

    init {
        this.responseText.append("$httpVersion ${app.http.StatusCode.OK.code} ${app.http.StatusCode.OK.description}\r\n")
        headers.addHeader("Date:${java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME.format(java.time.ZonedDateTime.now(java.time.ZoneId.of("GMT")))}")
        headers.addHeader("Connection:close")
        headers.addHeader("Content-Length:${this.responseBody.size}")
        headers.addHeader("Content-Type:application/json;charset=utf-8")
        this.responseText.append(headers.getHeaderList().listHeaders())
    }

    override fun getResponseText(): String = this.responseText.toString()

    private fun parseJsonToUtf8(body: ByteArray?) =
            if (body != null && body.size > 0) String(body).toByteArray(Charsets.UTF_8)
            else throw IllegalArgumentException("Null app.http.request body.")
}