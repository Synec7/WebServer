package app.http

import core.protocol.Request
import core.protocol.Response
import http.HttpMethod
import http.request.HttpRequest
import http.response.BadRequestHttpResponse
import http.response.HttpResponseBuilder
import http.response.NotImplementedHttpResponse

/**
 * Created by Vincente A. Campisi on 24/05/17.
 */
class MyCustomHttpResponseBuilder : HttpResponseBuilder() {
	override fun buildResponse(request: Request): Response =
			try {
				when ((request as HttpRequest).method) {
					HttpMethod.UNKNOWN -> BadRequestHttpResponse()
					HttpMethod.POST -> PostResponse(request)
					else -> {
						NotImplementedHttpResponse()
					}
				}
			} catch (iae: IllegalArgumentException) {
				BadRequestHttpResponse()
			}

}