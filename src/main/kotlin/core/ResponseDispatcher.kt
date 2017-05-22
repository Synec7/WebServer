package core

import java.net.Socket

/**
 * Created by Vincente A. Campisi on 22/05/17.
 */
interface ResponseDispatcher {
	fun sendDefaultResponse(socket: Socket)
	fun sendResponse(socket: Socket, response: Response)
}