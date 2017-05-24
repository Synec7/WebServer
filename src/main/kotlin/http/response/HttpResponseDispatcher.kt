package http.response

/**
* Created by Vincente A. Campisi on 22/05/17.
*/
class HttpResponseDispatcher : core.protocol.ResponseDispatcher {
	override fun sendDefaultResponse(socket: java.net.Socket) {
		sendResponse(socket, BadRequestHttpResponse())
	}

	override fun sendResponse(socket: java.net.Socket, response: core.protocol.Response) {
		val output = socket.getOutputStream()
		output.write("${response.getResponseText()}\r\n".toByteArray(Charsets.ISO_8859_1))
		(response as HttpResponse).responseBody?.let {
			output.write(response.responseBody)
		}

		output.flush()
	}
}