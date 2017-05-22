package app

import app.http.request.HttpRequestHandler
import app.http.response.HttpResponseBuilder
import app.http.response.HttpResponseDispatcher
import core.SocketServer
import java.net.ServerSocket

/**
* Created by Vincente A. Campisi on 05/04/17.
*/

fun main(args: Array<String>) {

	val socket = ServerSocket(60074)
	val server = SocketServer(socket, HttpRequestHandler(), HttpResponseBuilder(), HttpResponseDispatcher())

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
