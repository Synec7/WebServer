package core

import java.net.Socket

/**
 * Created by Vincente A. Campisi on 21/04/17.
 */
interface IRequestHandler {
    fun receiveRequest(socket: Socket): IRequest
}