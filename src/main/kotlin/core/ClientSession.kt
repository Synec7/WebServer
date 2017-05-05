package core

import mu.KLogging
import java.io.IOException
import java.net.Socket

/**
 * Created by Vincente A. Campisi on 20/03/17.
 */
class ClientSession(private val socket: Socket,
                    private val requestHandler: IRequestHandler,
                    private val responseHandler: IResponseHandler) : Runnable {

    companion object : KLogging()

    override fun run() {
        try {
            val request = requestHandler.receiveRequest(socket)
            responseHandler.sendResponse(socket,
                    responseHandler.buildResponse(request)
            )
        } catch (ioe: IOException) {
            logger.error(ioe) { ioe.message }
            responseHandler.sendDefaultResponse(socket)
        } catch (iae: IllegalArgumentException) {
            logger.error(iae) { iae.message }
            responseHandler.sendDefaultResponse(socket)
        } catch(e: Exception) {
            logger.error(e) { e.message }
            responseHandler.sendDefaultResponse(socket)
        } finally {
            this.socket.getOutputStream().close()
            this.socket.close()
        }
    }

}