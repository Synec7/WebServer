package app

import app.http.MyCustomHttpResponseBuilder
import core.SocketServer
import http.HttpProtocol
import http.request.HttpRequestHandler
import http.response.HttpResponseDispatcher
import java.net.ServerSocket

/**
* Created by Vincente A. Campisi on 05/04/17.
*/

fun main(args: Array<String>) {

	val socket = ServerSocket(60074)
	val protocol = HttpProtocol(HttpRequestHandler(), MyCustomHttpResponseBuilder(), HttpResponseDispatcher())
	val server = SocketServer(socket, protocol)

	Thread { server.listen() }.start()

	while(true) {
		val input = readLine()

		if(input == "exit") {
			Thread { server.shutdown() }.start()
			break
		}
	}
	socket.close()
}
