package http

import http.request.HttpRequestHandler
import http.response.HttpResponseBuilder
import http.response.HttpResponseDispatcher
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Vincente A. Campisi on 24/05/17.
 */
class HttpProtocolTest {

	val protocol = HttpProtocol(HttpRequestHandler(), HttpResponseBuilder(), HttpResponseDispatcher())

	@Test
	fun checkNotNull() {
		assertNotNull(protocol)
	}

	@Test
	fun getRequestHandler() {
		assertNotNull(protocol.getRequestHandler())
	}

	@Test
	fun getResponseBuilder() {
		assertNotNull(protocol.getResponseBuilder())
	}

	@Test
	fun getResponseDispatcher() {
		assertNotNull(protocol.getResponseDispatcher())
	}

}