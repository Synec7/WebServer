package core

import core.protocol.Protocol
import mu.KLogging
import java.io.IOException
import java.net.Socket

/**
* Created by Vincente A. Campisi on 20/03/17.
*/
class ClientSession(private val socket: Socket,
                    private val protocol: Protocol) : Runnable {

	companion object : KLogging()

	override fun run() {
		try {
			val request = protocol.getRequestHandler().receiveRequest(socket)
			val response = protocol.getResponseBuilder().buildResponse(request)
			protocol.getResponseDispatcher().sendResponse(socket, response)
		} catch (ioe: IOException) {
			logger.error(ioe) { ioe.message }
			protocol.getResponseDispatcher().sendDefaultResponse(socket)
		} catch (iae: IllegalArgumentException) {
			logger.error(iae) { iae.message }
			protocol.getResponseDispatcher().sendDefaultResponse(socket)
		} catch(e: Exception) {
			logger.error(e) { e.message }
			protocol.getResponseDispatcher().sendDefaultResponse(socket)
		} finally {
			this.socket.getOutputStream().close()
			this.socket.close()
		}
	}

}