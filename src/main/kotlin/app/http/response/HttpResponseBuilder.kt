package app.http.response

import core.Request
import core.Response
import core.ResponseBuilder
import http.HttpMethod
import http.HttpRequest
import http.getHeader

/**
 * Created by Vincente A. Campisi on 22/05/17.
 */
class HttpResponseBuilder : ResponseBuilder {
	override fun buildResponse(request: Request): Response =
			when ((request as HttpRequest).method) {
				HttpMethod.POST -> buildPost(request.headers.getHeader("Content-Type"), request.body)
				HttpMethod.UNKNOWN -> BadRequestHttpResponse()
				else -> {
					NotImplementedHttpResponse()
				}
			}

	private fun buildPost(contentType: String, body: ByteArray?): HttpResponse =
			when (contentType) {
				"application/octet-stream" -> BinaryHttpResponse(body)
				"application/json" -> JsonHttpResponse(body)
				else -> {
					BadRequestHttpResponse()
				}
			}
}