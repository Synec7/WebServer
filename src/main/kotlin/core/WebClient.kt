package core

import request.RequestHandler
import response.BadRequestResponse
import response.ResponseHandler
import java.io.IOException
import java.net.Socket

/**
 * Created by Vincente A. Campisi on 20/03/17.
 */
class WebClient(private val socket: Socket) : Runnable {

    private val requestHandler = RequestHandler(this.socket.getInputStream())
    private val responseHandler = ResponseHandler(this.socket.getOutputStream())
    //private val functionality: Map<(request: Request) -> Boolean, () -> Any>? = null

    override fun run() {
        try {
            val request = requestHandler.receiveRequest()
            responseHandler.sendResponse(
                    responseHandler.buildResponse(request)
            )
        } catch (ioe: IOException) {
            responseHandler.sendResponse(
                    BadRequestResponse()
            )
        } catch (iae: IllegalArgumentException) {
            responseHandler.sendResponse(
                    BadRequestResponse()
            )
        } catch(e: Exception) {
            responseHandler.sendResponse(
                    BadRequestResponse()
            )
        } finally {
            this.socket.getOutputStream().close()
            this.socket.close()
        }
    }

}