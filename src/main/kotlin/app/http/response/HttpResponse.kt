package app.http.response

import core.Response
import http.Headers

/**u
 * Created by Vincente A. Campisi on 10/04/17.
 */
interface HttpResponse : Response {
    val responseText: StringBuilder
    val headers: Headers
    val httpVersion: String
    val responseBody: ByteArray?
}