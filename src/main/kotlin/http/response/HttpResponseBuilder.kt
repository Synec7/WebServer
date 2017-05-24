package http.response

import core.protocol.Request
import core.protocol.Response

/**
* Created by Vincente A. Campisi on 22/05/17.
*/
open class HttpResponseBuilder : core.protocol.ResponseBuilder {

	override fun buildResponse(request: Request): Response =
			try {
				when ((request as http.request.HttpRequest).method) {
					http.HttpMethod.UNKNOWN -> BadRequestHttpResponse()
					else -> {
						NotImplementedHttpResponse()
					}
				}
			} catch (iae: IllegalArgumentException) {
				BadRequestHttpResponse()
			} catch (e: Exception) {
				BadRequestHttpResponse()
			}

}