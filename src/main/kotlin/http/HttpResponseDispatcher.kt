package http

import core.protocol.Response
import core.protocol.ResponseDispatcher
import java.net.Socket

/**
* Created by Vincente A. Campisi on 22/05/17.
*/
class HttpResponseDispatcher : ResponseDispatcher {
	override fun sendDefaultResponse(socket: Socket) {
		sendResponse(socket, BadRequestHttpResponse())
	}

	override fun sendResponse(socket: Socket, response: Response) {
		val output = socket.getOutputStream()
		output.write("${response.getResponseText()}\r\n".toByteArray(Charsets.ISO_8859_1))
		(response as HttpResponse).responseBody?.let {
			output.write(response.responseBody)
		}

		output.flush()
	}
}