package app

import core.SocketServer
import http.HttpProtocol
import java.net.ServerSocket

/**
* Created by Vincente A. Campisi on 05/04/17.
*/

fun main(args: Array<String>) {

	val socket = ServerSocket(60074)
	val server = SocketServer(socket, HttpProtocol())

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
