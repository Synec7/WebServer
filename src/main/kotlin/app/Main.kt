package app

import app.http.request.HttpRequestHandler
import app.http.response.HttpResponseHandler
import core.SocketServer
import core.ServerShutdownHook
import java.net.ServerSocket

/**
 * Created by Vincente A. Campisi on 05/04/17.
 */

fun main(args: Array<String>) {

    val socket = ServerSocket(60074)
    val server = SocketServer(socket, HttpRequestHandler(), HttpResponseHandler())
    server.getRuntime().addShutdownHook(
            Thread(ServerShutdownHook(server.getExecutor(), server.clientConnections))
    )

    Thread{server.listen()}.start()

    var input = readLine()
    while (input != "exit") {
        input = readLine()
    }

    socket.close()
}
