package http

import core.protocol.Protocol
import core.protocol.RequestHandler
import core.protocol.ResponseBuilder
import core.protocol.ResponseDispatcher

/**
* Created by Vincente A. Campisi on 24/05/17.
*/
class HttpProtocol : Protocol {
	private val requestHandler = HttpRequestHandler()
	private val responseBuilder = HttpResponseBuilder()
	private val responseDispatcher = HttpResponseDispatcher()

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