package http

import app.http.PostResponse
import org.junit.Test

/**
 * Created by Vincente A. Campisi on 24/05/17.
 */
class HttpResponseBuilderTest {
	@Test
	fun buildResponse() {
		val responseBuilder = HttpResponseBuilder()
		var request = HttpRequest(HttpMethod.POST, listOf(HttpHeader("Content-Type", "application/json"), HttpHeader("Content-Length", "3")), "abc".toByteArray(Charsets.ISO_8859_1))
		assert(responseBuilder.buildResponse(request) is PostResponse)

		request = HttpRequest(HttpMethod.UNKNOWN, listOf(HttpHeader("", "")), null)
		assert(responseBuilder.buildResponse(request) is BadRequestHttpResponse)

		request = HttpRequest(HttpMethod.GET, listOf(HttpHeader("", "")), null)
		assert(responseBuilder.buildResponse(request) is NotImplementedHttpResponse)
	}

}