package core

import java.net.Socket

/**
 * Created by Vincente A. Campisi on 27/03/17.
 */
class ServerShutdownHook(private val clientConnections: MutableList<Socket>) : Runnable {
    override fun run() {
        try {
            val logger = SocketServer.logger
            for (s: Socket in clientConnections)
                s.close()
            logger.info("SocketServer shut down complete.")
        } catch (ignoredException: Exception) {
        }
    }
}