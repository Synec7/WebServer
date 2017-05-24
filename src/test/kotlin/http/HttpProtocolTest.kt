package http

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Vincente A. Campisi on 24/05/17.
 */
class HttpProtocolTest {

	val protocol = HttpProtocol()

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