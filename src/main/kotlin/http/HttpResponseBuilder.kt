package http

import app.http.BadRequestHttpResponse
import app.http.NotImplementedHttpResponse
import app.http.PostResponse
import core.Request
import core.Response
import core.ResponseBuilder

/**
* Created by Vincente A. Campisi on 22/05/17.
*/
class HttpResponseBuilder : ResponseBuilder {

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
//	} catch (re: RuntimeException) {
//		InternalServerErrorResponse()
//	}
}