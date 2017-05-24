package core.protocol

/**
 * Created by Vincente A. Campisi on 21/04/17.
 */
interface RequestHandler {
    fun receiveRequest(socket: java.net.Socket): Request
}