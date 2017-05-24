package http

import core.protocol.Protocol
import core.protocol.RequestHandler
import core.protocol.ResponseBuilder
import core.protocol.ResponseDispatcher
import http.request.HttpRequestHandler
import http.response.HttpResponseBuilder
import http.response.HttpResponseDispatcher

/**
* Created by Vincente A. Campisi on 24/05/17.
*/
class HttpProtocol(private val requestHandler: HttpRequestHandler,
                   private val responseBuilder: HttpResponseBuilder,
                   private val responseDispatcher: HttpResponseDispatcher) : Protocol {

	override fun getRequestHandler(): RequestHandler {
		return requestHandler
	}

	override fun getResponseBuilder(): ResponseBuilder {
		return responseBuilder
	}

	override fun getResponseDispatcher(): ResponseDispatcher {
		return responseDispatcher
	}
}