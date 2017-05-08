package app.http.response

import app.http.listHeaders

/**
 * Created by Vincente A. Campisi on 10/04/17.
 */
class BinaryHttpResponse(requestBody: ByteArray?) : HttpResponse {
    override val responseText = StringBuilder()
    override val headers = app.http.Headers()
    override val httpVersion = "HTTP/1.1"
    override val responseBody: ByteArray = calculateHash(requestBody).toByteArray(Charsets.UTF_8)

    init {
        this.responseText.append("$httpVersion ${app.http.StatusCode.OK.code} ${app.http.StatusCode.OK.description}\r\n")
        headers.addHeader("Date:${java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME.format(java.time.ZonedDateTime.now(java.time.ZoneId.of("GMT")))}")
        headers.addHeader("Connection:close")
        headers.addHeader("Content-Length:${this.responseBody.size}")
        headers.addHeader("Content-Type:text/plain;charset=utf-8")
        this.responseText.append(headers.getHeaderList().listHeaders())
    }

    override fun getResponseText(): String = this.responseText.toString()

    private fun calculateHash(body: ByteArray?) =
            if (body != null && body.size > 0) byteArrayToHex(java.security.MessageDigest.getInstance("MD5").digest(body))
            else throw IllegalArgumentException("Null app.http.request body.")

    private fun byteArrayToHex(a: ByteArray): String {
        val sb = StringBuilder(a.size * 2)
        for (b in a)
            sb.append(String.format("%02x", b))
        return sb.toString()
    }

}