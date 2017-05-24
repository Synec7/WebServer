package http

import core.protocol.Request

/**
 * Created by Vincente A. Campisi on 22/05/17.
 */
class HttpRequest(val method: HttpMethod, val headers: List<HttpHeader>, var body: ByteArray? = null) : Request