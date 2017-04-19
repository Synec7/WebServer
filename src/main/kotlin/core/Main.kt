package core

import java.net.ServerSocket

/**
 * Created by Vincente A. Campisi on 05/04/17.
 */

fun main(args: Array<String>) {
    val socket = ServerSocket(60074)
    val server = WebServer(socket)

    Thread(server).start()

    var input = readLine()
    while (input != "exit") {
        input = readLine()
    }

    socket.close()
}