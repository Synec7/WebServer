package http.request

import http.getHeader

/**
* Created by Vincente A. Campisi on 03/04/17.
*/
class HttpRequestHandler : core.protocol.RequestHandler {

	override fun receiveRequest(socket: java.net.Socket): HttpRequest {
		val requestReader = HttpRequestReader(socket.getInputStream())
		val request = HttpRequest(requestReader.readMethod(), requestReader.readHeaders())

		if (request.method == http.HttpMethod.POST) {
			try {
				receiveRequestBody(socket, request.headers.getHeader("content-length").toInt(), request)
			} catch (nfe: NumberFormatException) {
				throw IllegalArgumentException("Invalid header format.")
			}
		}
		return request
	}

	private fun receiveRequestBody(socket: java.net.Socket, contentLength: Int, httpRequest: HttpRequest) {
		val requestReader = HttpRequestReader(socket.getInputStream())
		if (contentLength <= 0) {
			throw IllegalStateException("Invalid content-length header.")
		}

		val requestBody = ByteArray(contentLength)
		val bytes = requestReader.readBytes(contentLength, requestBody)

		if (bytes != contentLength) {
			throw IllegalStateException("Content Length does not match number of bytes read.")
		}

		httpRequest.body = requestBody
	}
}