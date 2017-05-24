package core.protocol

/**
 * Created by Vincente A. Campisi on 22/05/17.
 */
interface ResponseDispatcher {
	fun sendDefaultResponse(socket: java.net.Socket)
	fun sendResponse(socket: java.net.Socket, response: Response)
}