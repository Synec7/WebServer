package http.response

/**
 * Created by Vincente A. Campisi on 10/04/17.
 */
interface HttpResponse : core.protocol.Response {
    val responseText: StringBuilder
    val headers: http.Headers
    val httpVersion: String
    var responseBody: ByteArray?
}