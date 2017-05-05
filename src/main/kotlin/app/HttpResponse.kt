package app

import core.IResponse
import http.Headers

/**u
 * Created by Vincente A. Campisi on 10/04/17.
 */
interface HttpResponse : IResponse {
    val responseText: StringBuilder
    val headers: Headers
    val httpVersion: String
    val responseBody: ByteArray?
}