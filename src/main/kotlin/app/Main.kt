package app

import core.Server
import core.ServerShutdownHook
import java.net.ServerSocket

/**
 * Created by Vincente A. Campisi on 05/04/17.
 */

fun main(args: Array<String>) {

    val socket = ServerSocket(60074)
    val server = Server(socket, HttpRequestHandler(), HttpResponseHandler())
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
