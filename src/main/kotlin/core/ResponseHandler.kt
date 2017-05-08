package core

import java.net.Socket

/**
 * Created by Vincente A. Campisi on 21/04/17.
 */
interface ResponseHandler {
    fun buildResponse(request: Request): Response
    fun sendDefaultResponse(socket: Socket)
    fun sendResponse(socket: Socket, response: Response)
}