package http.request

import core.protocol.Request
import http.HttpHeader
import http.HttpMethod

/**
 * Created by Vincente A. Campisi on 22/05/17.
 */
class HttpRequest(val method: HttpMethod, val headers: List<HttpHeader>, var body: ByteArray? = null) : Request