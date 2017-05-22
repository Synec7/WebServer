package core

import mu.KLogging
import java.io.IOException
import java.net.Socket

/**
 * Created by Vincente A. Campisi on 20/03/17.
 */
class ClientSession(private val socket: Socket,
                    private val requestHandler: RequestHandler,
                    private val responseBuilder: ResponseBuilder,
                    private val responseDispatcher: ResponseDispatcher) : Runnable {

    companion object : KLogging()

    override fun run() {
        try {
            val request = requestHandler.receiveRequest(socket)
            val response = responseBuilder.buildResponse(request)
            responseDispatcher.sendResponse(socket, response)
        } catch (ioe: IOException) {
            logger.error(ioe) { ioe.message }
            responseDispatcher.sendDefaultResponse(socket)
        } catch (iae: IllegalArgumentException) {
            logger.error(iae) { iae.message }
            responseDispatcher.sendDefaultResponse(socket)
        } catch(e: Exception) {
            logger.error(e) { e.message }
            responseDispatcher.sendDefaultResponse(socket)
        } finally {
            this.socket.getOutputStream().close()
            this.socket.close()
        }
    }

}