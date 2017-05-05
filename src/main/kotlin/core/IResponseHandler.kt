package core

import java.net.Socket

/**
 * Created by Vincente A. Campisi on 21/04/17.
 */
interface IResponseHandler {
    fun buildResponse(request: IRequest): IResponse
    fun sendDefaultResponse(socket: Socket)
    fun sendResponse(socket: Socket, response: IResponse)
}